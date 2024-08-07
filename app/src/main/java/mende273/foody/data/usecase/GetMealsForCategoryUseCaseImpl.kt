package mende273.foody.data.usecase

import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetMealsForCategoryUseCase

class GetMealsForCategoryUseCaseImpl
(private val remoteRepository: RemoteRepository) : GetMealsForCategoryUseCase {
    override suspend fun invoke(category: String): Result<List<Meal>> =
        remoteRepository.getMealsForCategory(category)
}
