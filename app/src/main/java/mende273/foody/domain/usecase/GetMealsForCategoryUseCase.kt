package mende273.foody.domain.usecase

import mende273.foody.domain.model.Meal

fun interface GetMealsForCategoryUseCase {
    suspend operator fun invoke(category: String): Result<List<Meal>>
}
