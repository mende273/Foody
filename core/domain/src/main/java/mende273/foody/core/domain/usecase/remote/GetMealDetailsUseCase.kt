package mende273.foody.core.domain.usecase.remote

import mende273.foody.core.domain.model.MealDetails

fun interface GetMealDetailsUseCase {
    suspend operator fun invoke(id: Long): Result<MealDetails>
}
