package mende273.foody.domain.usecase.remote

import mende273.foody.domain.model.Meal

fun interface GetMealsForFirstLetterUseCase {
    suspend operator fun invoke(letter: String): Result<List<Meal>>
}
