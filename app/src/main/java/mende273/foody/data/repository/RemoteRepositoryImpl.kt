package mende273.foody.data.repository

import mende273.foody.data.api.ApiService
import mende273.foody.data.dto.MealCategoriesDto
import mende273.foody.data.dto.MealsDto
import mende273.foody.data.dto.MealsWithDetailsDto
import mende273.foody.util.exception.RetrieveNetworkDataException

class RemoteRepositoryImpl(private val apiService: ApiService) : RemoteRepository {

    override suspend fun getRandomMeal(): Result<MealsWithDetailsDto?> {
        return try {
            Result.success(apiService.getRandomMeal())
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealsForCategory(category: String): Result<MealsDto?> {
        return try {
            Result.success(apiService.getMealsForCategory(category))
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealsForArea(area: String): Result<MealsDto?> {
        return try {
            Result.success(apiService.getMealsForArea(area))
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealDetails(id: String): Result<MealsWithDetailsDto?> {
        return try {
            Result.success(apiService.getMealDetails(id))
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }

    override suspend fun getMealCategories(): Result<MealCategoriesDto?> {
        return try {
            Result.success(apiService.getMealCategories())
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }
}
