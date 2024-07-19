package mende273.foody.domain.usecase

import mende273.foody.domain.model.MealDetails

fun interface GetMealDetailsUseCase {
    suspend operator fun invoke(id: Long): Result<MealDetails>
}
