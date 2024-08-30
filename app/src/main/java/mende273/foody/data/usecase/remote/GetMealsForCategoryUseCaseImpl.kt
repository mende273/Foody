package mende273.foody.data.usecase.remote

import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.RemoteRepository
import mende273.foody.core.domain.usecase.remote.GetMealsForCategoryUseCase

class GetMealsForCategoryUseCaseImpl
(private val remoteRepository: RemoteRepository) : GetMealsForCategoryUseCase {
    override suspend fun invoke(category: String): Result<List<Meal>> =
        remoteRepository.getMealsForCategory(category)
}
