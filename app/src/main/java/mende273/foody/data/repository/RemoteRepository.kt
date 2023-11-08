package mende273.foody.data.repository

import mende273.foody.data.dto.MealsDto
import mende273.foody.data.dto.MealsWithDetailsDto
import mende273.foody.domain.model.Meal

interface RemoteRepository {
    suspend fun getRandomMeal(): Result<MealsWithDetailsDto?>
    suspend fun getMealsForCategory(category: String): Result<MealsDto?>
    suspend fun getMealsForArea(area: String): Result<MealsDto?>
    suspend fun getMealsForFirstLetter(letter: String): Result<MealsDto?>
    suspend fun getMealsWithIngredient(ingredient: String): Result<MealsDto?>
    suspend fun getMealDetails(id: String): Result<MealsWithDetailsDto?>
    suspend fun getMealCategories(): Result<List<String>>
    suspend fun getMealAreas(): Result<List<String>>
    suspend fun searchMealsByName(name: String): Result<List<Meal>>
}
