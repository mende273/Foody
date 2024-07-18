package mende273.foody.domain.usecase

import mende273.foody.domain.model.Meal

fun interface GetAllFavoriteMealsFromDBUseCase {
    suspend operator fun invoke(): List<Meal>
}
