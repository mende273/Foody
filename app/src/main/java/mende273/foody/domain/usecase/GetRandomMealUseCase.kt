package mende273.foody.domain.usecase

import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.model.MealDetails
import mende273.foody.mapper.toDomain
import mende273.foody.util.exception.RetrieveNetworkDataException

class GetRandomMealUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(): Result<MealDetails> {
        return remoteRepository.getRandomMeal().fold(
            onSuccess = { mealsDto ->
                val mealDetails: MealDetails? = mealsDto?.takeIf { it.mealsWithDetails != null }
                    ?.toDomain()
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
