package mende273.foody.domain.usecase.remote

import mende273.foody.domain.model.Meal

fun interface GetMealsWithIngredientUseCase {
    suspend operator fun invoke(ingredient: String): Result<List<Meal>>
}
