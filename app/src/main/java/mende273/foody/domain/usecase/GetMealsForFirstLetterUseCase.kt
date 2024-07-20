package mende273.foody.domain.usecase

import mende273.foody.domain.model.Meal

fun interface GetMealsForFirstLetterUseCase {
    suspend operator fun invoke(letter: String): Result<List<Meal>>
}
