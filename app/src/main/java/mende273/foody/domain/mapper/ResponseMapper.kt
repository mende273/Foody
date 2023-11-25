package mende273.foody.domain.mapper

import mende273.foody.data.dto.MealCategoriesDto
import mende273.foody.data.dto.MealDetailsDto
import mende273.foody.data.dto.MealDto
import mende273.foody.data.dto.MealsDto
import mende273.foody.data.dto.MealsWithDetailsDto
import mende273.foody.domain.model.IngredientWithMeasure
import mende273.foody.domain.model.Meal
import mende273.foody.domain.model.MealDetails

fun MealsWithDetailsDto?.toModel(): List<MealDetails> {
    return this?.mealsWithDetails?.filterNotNull()?.map { it.toModel() } ?: emptyList()
}

fun MealDetailsDto.toModel(): MealDetails = MealDetails(
    id = id ?: 0,
    name = name ?: "",
    category = category ?: "",
    area = area ?: "",
    instructions = instructions ?: "",
    thumb = thumb ?: "",
    tags = tags ?: "",
    youtube = youtube ?: "",
    source = source ?: "",
    ingredientsWithMeasures = makeListForIngredientsWithMeasures(this)
)

fun MealsDto?.toModel(): List<Meal> =
    this?.meals?.filterNotNull()?.map { it.toModel() } ?: emptyList()

fun MealDto.toModel(): Meal = Meal(
    id = id,
    thumb = thumb ?: "",
    name = name ?: ""
)

fun MealCategoriesDto?.toModel(): List<String> =
    this?.categories
        ?.filterNotNull()
        .takeIf { !it.isNullOrEmpty() }
        ?.mapNotNull {
            it.category
        }?.filter {
            it.trim().isNotEmpty()
        }
        ?: emptyList()

private fun makeListForIngredientsWithMeasures(item: MealDetailsDto): List<IngredientWithMeasure> {
    val items = mutableListOf<IngredientWithMeasure>()

    with(item) {
        items.addIngredientWithMeasure(ingredient1, measure1)
        items.addIngredientWithMeasure(ingredient2, measure2)
        items.addIngredientWithMeasure(ingredient3, measure3)
        items.addIngredientWithMeasure(ingredient4, measure4)
        items.addIngredientWithMeasure(ingredient5, measure5)
        items.addIngredientWithMeasure(ingredient6, measure6)
        items.addIngredientWithMeasure(ingredient7, measure7)
        items.addIngredientWithMeasure(ingredient8, measure8)
        items.addIngredientWithMeasure(ingredient9, measure9)
        items.addIngredientWithMeasure(ingredient10, measure10)
        items.addIngredientWithMeasure(ingredient11, measure11)
        items.addIngredientWithMeasure(ingredient12, measure12)
        items.addIngredientWithMeasure(ingredient13, measure13)
        items.addIngredientWithMeasure(ingredient14, measure14)
        items.addIngredientWithMeasure(ingredient15, measure15)
        items.addIngredientWithMeasure(ingredient16, measure16)
        items.addIngredientWithMeasure(ingredient17, measure17)
        items.addIngredientWithMeasure(ingredient18, measure18)
        items.addIngredientWithMeasure(ingredient19, measure19)
        items.addIngredientWithMeasure(ingredient20, measure20)
    }

    return items
}

private fun MutableList<IngredientWithMeasure>.addIngredientWithMeasure(
    ingredient: String?,
    measure: String?
) {
    if (!ingredient.isNullOrEmpty() && !measure.isNullOrEmpty()) {
        add(IngredientWithMeasure(ingredient, measure))
    }
}
