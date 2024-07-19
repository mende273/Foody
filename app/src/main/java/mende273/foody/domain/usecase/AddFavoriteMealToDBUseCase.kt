package mende273.foody.domain.usecase

import mende273.foody.domain.model.Meal

fun interface AddFavoriteMealToDBUseCase {
    suspend operator fun invoke(meal: Meal)
}
