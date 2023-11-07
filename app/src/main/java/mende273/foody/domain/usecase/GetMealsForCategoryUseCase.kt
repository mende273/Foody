package mende273.foody.domain.usecase

import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.mapper.toDomain
import mende273.foody.domain.model.Meal

class GetMealsForCategoryUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(category: String): Result<List<Meal>> {
        return remoteRepository.getMealsForCategory(category).fold(
            onSuccess = { Result.success(it.toDomain()) },
            onFailure = { Result.failure(it) }
        )
    }
}
