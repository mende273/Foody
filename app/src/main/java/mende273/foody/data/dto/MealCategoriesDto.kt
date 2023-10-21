package mende273.foody.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MealCategoriesDto(
    @SerialName("meals")
    val categories: List<MealCategoryDto?>?
)
