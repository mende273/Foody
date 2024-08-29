package mende273.foody.core.domain.repository

import kotlinx.coroutines.flow.Flow
import mende273.foody.core.domain.model.Meal

interface LocalRepository {
    suspend fun getAllFavouriteMeals(): List<Meal>
    suspend fun getFavouriteMealById(id: Long): Flow<Meal?>
    suspend fun addFavouriteMeal(meal: Meal)
    suspend fun deleteFavouriteMeal(meal: Meal)
}
