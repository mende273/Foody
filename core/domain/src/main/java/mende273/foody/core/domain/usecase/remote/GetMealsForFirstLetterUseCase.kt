package mende273.foody.core.domain.usecase.remote

import mende273.foody.core.domain.model.Meal

fun interface GetMealsForFirstLetterUseCase {
    suspend operator fun invoke(letter: String): Result<List<Meal>>
}
