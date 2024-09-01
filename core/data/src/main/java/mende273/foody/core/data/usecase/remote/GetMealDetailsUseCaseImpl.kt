package mende273.foody.core.data.usecase.remote

import mende273.foody.core.domain.model.MealDetails
import mende273.foody.core.domain.repository.RemoteRepository
import mende273.foody.core.domain.usecase.remote.GetMealDetailsUseCase

class GetMealDetailsUseCaseImpl
(private val remoteRepository: RemoteRepository) : GetMealDetailsUseCase {
    override suspend fun invoke(id: Long): Result<MealDetails> =
        remoteRepository.getMealDetails(id)
}
