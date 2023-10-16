package mende273.foody.data.repository

import mende273.foody.data.dto.MealsDto

interface RemoteRepository {
    suspend fun getRandomMeal(): Result<MealsDto?>
}
