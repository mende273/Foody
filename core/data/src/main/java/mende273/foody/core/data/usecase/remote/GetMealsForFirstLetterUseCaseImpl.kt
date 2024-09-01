package mende273.foody.core.data.usecase.remote

import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.RemoteRepository
import mende273.foody.core.domain.usecase.remote.GetMealsForFirstLetterUseCase

class GetMealsForFirstLetterUseCaseImpl(
    private val remoteRepository: RemoteRepository
) : GetMealsForFirstLetterUseCase {
    override suspend fun invoke(letter: String): Result<List<Meal>> =
        remoteRepository.getMealsForFirstLetter(letter)
}
