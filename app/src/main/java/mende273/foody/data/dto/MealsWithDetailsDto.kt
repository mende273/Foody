package mende273.foody.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MealsWithDetailsDto(
    @SerialName("meals")
    val mealsWithDetails: List<MealDetailsDto?>?
)
