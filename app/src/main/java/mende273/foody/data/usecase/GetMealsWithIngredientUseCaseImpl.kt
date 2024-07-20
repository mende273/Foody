package mende273.foody.data.usecase

import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetMealsWithIngredientUseCase

class GetMealsWithIngredientUseCaseImpl(
    private val remoteRepository: RemoteRepository
) : GetMealsWithIngredientUseCase {
    override suspend fun invoke(ingredient: String): Result<List<Meal>> =
        remoteRepository.getMealsWithIngredient(ingredient)
}
