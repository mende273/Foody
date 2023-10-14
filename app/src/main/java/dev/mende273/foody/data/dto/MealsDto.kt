package dev.mende273.foody.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MealsDto(
    @SerialName("meals")
    val meals: List<MealDto?>?
)
