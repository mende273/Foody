package mende273.foody.domain.usecase

import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.mapper.toDomain
import mende273.foody.util.exception.RetrieveNetworkDataException

class GetMealCategoriesUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(): Result<List<String>> {
        return remoteRepository.getMealCategories().fold(
            onSuccess = { mealCategoriesDto ->
                mealCategoriesDto?.let {
                    Result.success(it.toDomain())
                } ?: Result.failure(RetrieveNetworkDataException())
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }
}
