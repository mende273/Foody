package mende273.foody.core.domain.usecase.remote

import mende273.foody.core.domain.model.Meal

fun interface GetMealsForCategoryUseCase {
    suspend operator fun invoke(category: String): Result<List<Meal>>
}
