package mende273.foody.core.domain.usecase.local

import mende273.foody.core.domain.model.Meal

fun interface DeleteFavoriteMealFromDBUseCase {
    suspend operator fun invoke(meal: Meal)
}
