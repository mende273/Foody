package mende273.foody.data.repository

import io.ktor.client.call.body
import io.ktor.http.isSuccess
import mende273.foody.data.api.ApiService
import mende273.foody.data.dto.MealCategoriesDto
import mende273.foody.data.dto.MealsDto
import mende273.foody.data.dto.MealsWithDetailsDto
import mende273.foody.util.exception.RetrieveNetworkDataException

class RemoteRepositoryImpl(private val apiService: ApiService) : RemoteRepository {

    override suspend fun getRandomMeal(): Result<MealsWithDetailsDto?> {
        return try {
            val call = apiService.getRandomMeal()
            if (call.status.isSuccess()) {
                Result.success(call.body())
            } else {
                Result.failure(RetrieveNetworkDataException())
            }
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealsForCategory(category: String): Result<MealsDto?> {
        return try {
            val call = apiService.getMealsForCategory(category)
            if (call.status.isSuccess()) {
                Result.success(call.body())
            } else {
                Result.failure(RetrieveNetworkDataException())
            }
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealsForArea(area: String): Result<MealsDto?> {
        return try {
            val call = apiService.getMealsForArea(area)
            if (call.status.isSuccess()) {
                Result.success(call.body())
            } else {
                Result.failure(RetrieveNetworkDataException())
            }
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealsWithIngredient(ingredient: String): Result<MealsDto?> {
        return try {
            val call = apiService.getMealsWithIngredient((ingredient))
            if (call.status.isSuccess()) {
                Result.success(call.body())
            } else {
                Result.failure(RetrieveNetworkDataException())
            }
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealsForFirstLetter(letter: String): Result<MealsDto?> {
        return try {
            val call = apiService.getMealsForFirstLetter(letter)
            if (call.status.isSuccess()) {
                Result.success(call.body())
            } else {
                Result.failure(RetrieveNetworkDataException())
            }
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealDetails(id: String): Result<MealsWithDetailsDto?> {
        return try {
            val call = apiService.getMealDetails(id)
            if (call.status.isSuccess()) {
                Result.success(call.body())
            } else {
                Result.failure(RetrieveNetworkDataException())
            }
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealCategories(): Result<MealCategoriesDto?> {
        return try {
            val call = apiService.getMealCategories()
            if (call.status.isSuccess()) {
                Result.success(call.body())
            } else {
                Result.failure(RetrieveNetworkDataException())
            }
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealAreas(): Result<MealCategoriesDto?> {
        return try {
            val call = apiService.getMealAreas()
            if (call.status.isSuccess()) {
                Result.success(call.body())
            } else {
                Result.failure(RetrieveNetworkDataException())
            }
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun searchMealsByName(name: String): Result<MealsDto?> {
        return runCatching {
            return@runCatching apiService.searchMealsByName(name).body()
        }
    }
}
