package mende273.foody.core.domain.usecase.remote

import mende273.foody.core.domain.model.Meal

fun interface SearchMealsByNameUseCase {
    suspend operator fun invoke(name: String): Result<List<Meal>>
}
