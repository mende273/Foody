package mende273.foody.domain.usecase

import mende273.foody.domain.model.Meal

fun interface SearchMealsByNameUseCase {
    suspend operator fun invoke(name: String): Result<List<Meal>>
}
