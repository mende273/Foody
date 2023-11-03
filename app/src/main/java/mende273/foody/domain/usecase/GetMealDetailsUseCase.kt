package mende273.foody.domain.usecase

import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.mapper.toDomain
import mende273.foody.domain.model.MealDetails
import mende273.foody.util.exception.RetrieveNetworkDataException

class GetMealDetailsUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(id: String): Result<MealDetails> {
        return remoteRepository.getMealDetails(id).fold(
            onSuccess = { mealsWithDetails ->
                val mealDetails: MealDetails? =
                    mealsWithDetails?.takeIf { it.mealsWithDetails != null }
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
