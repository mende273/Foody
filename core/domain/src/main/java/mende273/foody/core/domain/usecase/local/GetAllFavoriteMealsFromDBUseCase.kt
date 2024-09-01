package mende273.foody.core.domain.usecase.local

import mende273.foody.core.domain.model.Meal

fun interface GetAllFavoriteMealsFromDBUseCase {
    suspend operator fun invoke(): List<Meal>
}
