package mende273.foody.data.repository

import io.ktor.client.call.body
import mende273.foody.data.api.ApiService
import mende273.foody.data.dto.MealCategoriesDto
import mende273.foody.data.dto.MealsDto
import mende273.foody.data.dto.MealsWithDetailsDto
import mende273.foody.domain.mapper.toModel
import mende273.foody.domain.model.Meal
import mende273.foody.domain.model.MealDetails

class RemoteRepositoryImpl(private val apiService: ApiService) : RemoteRepository {

    override suspend fun getRandomMeal(): Result<MealDetails> =
        runCatching {
            apiService.getRandomMeal().body<MealsWithDetailsDto?>().toModel().first()
        }

    override suspend fun getMealsForCategory(category: String): Result<List<Meal>> =
        runCatching {
            apiService.getMealsForCategory(category).body<MealsDto?>().toModel()
        }

    override suspend fun getMealsForArea(area: String): Result<List<Meal>> =
        runCatching {
            apiService.getMealsForArea(area).body<MealsDto?>().toModel()
        }

    override suspend fun getMealsWithIngredient(ingredient: String): Result<List<Meal>> =
        runCatching {
            apiService.getMealsWithIngredient((ingredient)).body<MealsDto?>().toModel()
        }

    override suspend fun getMealsForFirstLetter(letter: String): Result<List<Meal>> =
        runCatching {
            apiService.getMealsForFirstLetter(letter).body<MealsDto?>().toModel()
        }

    override suspend fun getMealDetails(id: String): Result<MealDetails> =
        runCatching {
            apiService.getMealDetails(id).body<MealsWithDetailsDto?>().toModel().first()
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
