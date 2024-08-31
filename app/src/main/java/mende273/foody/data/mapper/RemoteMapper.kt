package mende273.foody.data.mapper

import mende273.foody.core.data.dto.MealCategoriesDto
import mende273.foody.core.data.dto.MealDetailsDto
import mende273.foody.core.data.dto.MealDto
import mende273.foody.core.data.dto.MealsDto
import mende273.foody.core.data.dto.MealsWithDetailsDto
import mende273.foody.core.domain.model.IngredientWithMeasure
import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.model.MealDetails

fun MealsWithDetailsDto?.toDomainModel(): List<MealDetails> {
    return this?.mealsWithDetails?.filterNotNull()?.map { it.toDomainModel() } ?: emptyList()
}

fun MealDetailsDto.toDomainModel(): MealDetails = MealDetails(
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

fun MealsDto?.toDomainModel(): List<Meal> =
    this?.meals?.filterNotNull()?.map { it.toDomainModel() } ?: emptyList()

fun MealDto.toDomainModel(): Meal = Meal(
    id = id,
    thumb = thumb ?: "",
    name = name ?: ""
)

fun MealCategoriesDto?.toDomainModel(): List<String> =
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
        items.addIngredientWithMeasure(ingredient1?.trim(), measure1?.trim())
        items.addIngredientWithMeasure(ingredient2?.trim(), measure2?.trim())
        items.addIngredientWithMeasure(ingredient3?.trim(), measure3?.trim())
        items.addIngredientWithMeasure(ingredient4?.trim(), measure4?.trim())
        items.addIngredientWithMeasure(ingredient5?.trim(), measure5?.trim())
        items.addIngredientWithMeasure(ingredient6?.trim(), measure6?.trim())
        items.addIngredientWithMeasure(ingredient7?.trim(), measure7?.trim())
        items.addIngredientWithMeasure(ingredient8?.trim(), measure8?.trim())
        items.addIngredientWithMeasure(ingredient9?.trim(), measure9?.trim())
        items.addIngredientWithMeasure(ingredient10?.trim(), measure10?.trim())
        items.addIngredientWithMeasure(ingredient11?.trim(), measure11?.trim())
        items.addIngredientWithMeasure(ingredient12?.trim(), measure12?.trim())
        items.addIngredientWithMeasure(ingredient13?.trim(), measure13?.trim())
        items.addIngredientWithMeasure(ingredient14?.trim(), measure14?.trim())
        items.addIngredientWithMeasure(ingredient15?.trim(), measure15?.trim())
        items.addIngredientWithMeasure(ingredient16?.trim(), measure16?.trim())
        items.addIngredientWithMeasure(ingredient17?.trim(), measure17?.trim())
        items.addIngredientWithMeasure(ingredient18?.trim(), measure18?.trim())
        items.addIngredientWithMeasure(ingredient19?.trim(), measure19?.trim())
        items.addIngredientWithMeasure(ingredient20?.trim(), measure20?.trim())
    }

    return items
}

private fun MutableList<IngredientWithMeasure>.addIngredientWithMeasure(
    ingredient: String?,
    measure: String?
) {
    if (!ingredient.isNullOrEmpty()) {
        add(IngredientWithMeasure(ingredient, measure ?: ""))
    }
}
