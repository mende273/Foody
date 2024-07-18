package mende273.foody.domain.usecase

import mende273.foody.domain.model.MealDetails

fun interface GetRandomMealUseCase {
    suspend operator fun invoke(): Result<MealDetails>
}
