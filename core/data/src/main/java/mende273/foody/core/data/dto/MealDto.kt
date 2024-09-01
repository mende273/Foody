package mende273.foody.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MealDto(
    @SerialName("idMeal") val id: Long,
    @SerialName("strMeal") val name: String?,
    @SerialName("strMealThumb") val thumb: String?
)
