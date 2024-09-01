package mende273.foody.core.data.mapper

import mende273.foody.MealEntity
import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.model.MealDetails

fun List<MealEntity>.toDomainModel(): List<Meal> {
    return this.map { it.toDomainModel() }
}

fun MealEntity.toDomainModel(): Meal = Meal(
    id = id,
    thumb = thumb,
    name = name
)

fun Meal.toEntityModel(): MealEntity = MealEntity(
    id = id,
    thumb = thumb,
    name = name
)

fun MealDetails.toMeal(): Meal = Meal(id = id, name = name, thumb = thumb)
