package mende273.foody.data.repository

import io.ktor.client.call.body
import mende273.foody.data.api.ApiService
import mende273.foody.data.dto.MealCategoriesDto
import mende273.foody.data.dto.MealsDto
import mende273.foody.data.dto.MealsWithDetailsDto
import mende273.foody.domain.mapper.toModel
import mende273.foody.domain.model.Meal

class RemoteRepositoryImpl(private val apiService: ApiService) : RemoteRepository {

    override suspend fun getRandomMeal(): Result<MealsWithDetailsDto?> =
        runCatching {
            apiService.getRandomMeal().body()
        }

    override suspend fun getMealsForCategory(category: String): Result<MealsDto?> =
        runCatching {
            apiService.getMealsForCategory(category).body()
        }

    override suspend fun getMealsForArea(area: String): Result<MealsDto?> =
        runCatching {
            apiService.getMealsForArea(area).body()
        }

    override suspend fun getMealsWithIngredient(ingredient: String): Result<MealsDto?> =
        runCatching {
            apiService.getMealsWithIngredient((ingredient)).body()
        }

    override suspend fun getMealsForFirstLetter(letter: String): Result<MealsDto?> =
        runCatching {
            apiService.getMealsForFirstLetter(letter).body()
        }

    override suspend fun getMealDetails(id: String): Result<MealsWithDetailsDto?> =
        runCatching {
            apiService.getMealDetails(id).body<MealsWithDetailsDto?>() // .toModel()
        }

    override suspend fun getMealCategories(): Result<List<String>> =
        runCatching {
            apiService.getMealCategories().body<MealCategoriesDto?>().toModel()
        }

    override suspend fun getMealAreas(): Result<List<String>> =
        runCatching {
            apiService.getMealAreas().body<MealCategoriesDto?>().toModel()
        }

    override suspend fun searchMealsByName(name: String): Result<List<Meal>> =
        runCatching { apiService.searchMealsByName(name).body<MealsDto?>().toModel() }
}
