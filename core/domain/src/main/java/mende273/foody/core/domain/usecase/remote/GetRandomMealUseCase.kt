package mende273.foody.core.domain.usecase.remote

import mende273.foody.core.domain.model.MealDetails

fun interface GetRandomMealUseCase {
    suspend operator fun invoke(): Result<MealDetails>
}
