package mende273.foody.data.repository

import mende273.foody.data.dto.MealsDto
import mende273.foody.data.dto.MealsWithDetailsDto

interface RemoteRepository {
    suspend fun getRandomMeal(): Result<MealsWithDetailsDto?>
    suspend fun getMealsForCategory(category: String): Result<MealsDto?>
    suspend fun getMealsForArea(area: String): Result<MealsDto?>
}