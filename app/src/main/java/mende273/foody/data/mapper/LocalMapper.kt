package mende273.foody.data.mapper

import mende273.foody.MealEntity
import mende273.foody.domain.model.Meal

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
