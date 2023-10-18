package mende273.foody.domain.model

data class MealDetails(
    val id: Long,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumb: String,
    val tags: List<String>,
    val youtube: String,
    val source: String,
    val ingredientsWithMeasures: List<IngredientWithMeasure>
)
