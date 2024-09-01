package mende273.foody.core.data.usecase.remote

import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.RemoteRepository
import mende273.foody.core.domain.usecase.remote.GetMealsWithIngredientUseCase

class GetMealsWithIngredientUseCaseImpl(
    private val remoteRepository: RemoteRepository
) : GetMealsWithIngredientUseCase {
    override suspend fun invoke(ingredient: String): Result<List<Meal>> =
        remoteRepository.getMealsWithIngredient(ingredient)
}
