package mende273.foody.domain.usecase.remote

import mende273.foody.domain.model.Meal

fun interface GetMealsForAreaUseCase {
    suspend operator fun invoke(area: String): Result<List<Meal>>
}
