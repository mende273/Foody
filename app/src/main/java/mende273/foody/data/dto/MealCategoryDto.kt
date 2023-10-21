package mende273.foody.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MealCategoryDto(
    @SerialName("strCategory")
    val category: String?
)
