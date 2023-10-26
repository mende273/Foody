package mende273.foody.data.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@OptIn(ExperimentalSerializationApi::class)
@Serializable
class MealCategoryDto(
    @JsonNames("strCategory", "strArea")
    val category: String?
)
