package mende273.foody.data.usecase

import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetMealsForFirstLetterUseCase

class GetMealsForFirstLetterUseCaseImpl(
    private val remoteRepository: RemoteRepository
) : GetMealsForFirstLetterUseCase {
    override suspend fun invoke(letter: String): Result<List<Meal>> =
        remoteRepository.getMealsForFirstLetter(letter)
}
