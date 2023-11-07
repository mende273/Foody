package mende273.foody.domain.usecase

import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.mapper.toModel
import mende273.foody.domain.model.Meal

class GetMealsForFirstLetterUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(letter: String): Result<List<Meal>> {
        return remoteRepository.getMealsForFirstLetter(letter).fold(
            onSuccess = { Result.success(it.toModel()) },
            onFailure = { Result.failure(it) }
        )
    }
}
