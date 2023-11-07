package mende273.foody.domain.usecase

import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.mapper.toModel
import mende273.foody.domain.model.MealDetails
import mende273.foody.util.exception.RetrieveNetworkDataException

class GetRandomMealUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(): Result<MealDetails> {
        return remoteRepository.getRandomMeal().fold(
            onSuccess = { mealsWithDetails ->
                val mealDetails: MealDetails? =
                    mealsWithDetails?.takeIf { it.mealsWithDetails != null }
                        ?.toModel()
                        ?.meals?.get(0)

                mealDetails?.let {
                    Result.success(it)
                } ?: Result.failure(RetrieveNetworkDataException())
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }
}
