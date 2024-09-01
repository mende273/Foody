package mende273.foody.core.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MealsDto(@SerialName("meals") val meals: List<MealDto?>?)
