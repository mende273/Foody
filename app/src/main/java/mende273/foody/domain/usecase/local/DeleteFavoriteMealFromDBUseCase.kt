package mende273.foody.domain.usecase.local

import mende273.foody.domain.model.Meal

fun interface DeleteFavoriteMealFromDBUseCase {
    suspend operator fun invoke(meal: Meal)
}
