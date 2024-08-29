package mende273.foody.core.domain.usecase.local

import mende273.foody.core.domain.model.Meal

fun interface AddFavoriteMealToDBUseCase {
    suspend operator fun invoke(meal: Meal)
}
