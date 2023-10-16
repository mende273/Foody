package dev.mende273.foody.data.repository

import dev.mende273.foody.data.api.ApiService
import dev.mende273.foody.data.dto.MealsDto
import dev.mende273.foody.util.exception.RetrieveNetworkDataException

class RemoteRepositoryImpl(private val apiService: ApiService) : RemoteRepository {

    override suspend fun getRandomMeal(): Result<MealsDto?> {
        return try {
            Result.success(apiService.getRandomMeal())
        } catch (e: RetrieveNetworkDataException) {
            Result.failure(e)
        }
    }
}
