package mende273.foody.data.repository

import mende273.foody.data.dto.MealsWithDetailsDto

interface RemoteRepository {
    suspend fun getRandomMeal(): Result<MealsWithDetailsDto?>
}
