package mende273.foody.core.domain.repository

import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.model.MealDetails

interface RemoteRepository {
    suspend fun getRandomMeal(): Result<MealDetails>
    suspend fun getMealsForCategory(category: String): Result<List<Meal>>
    suspend fun getMealsForArea(area: String): Result<List<Meal>>
    suspend fun getMealsForFirstLetter(letter: String): Result<List<Meal>>
    suspend fun getMealsWithIngredient(ingredient: String): Result<List<Meal>>
    suspend fun getMealDetails(id: Long): Result<MealDetails>
    suspend fun getMealCategories(): Result<List<String>>
    suspend fun getMealAreas(): Result<List<String>>
    suspend fun searchMealsByName(name: String): Result<List<Meal>>
}
