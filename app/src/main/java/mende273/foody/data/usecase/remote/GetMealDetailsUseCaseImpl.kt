package mende273.foody.data.usecase.remote

import mende273.foody.domain.model.MealDetails
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.remote.GetMealDetailsUseCase

class GetMealDetailsUseCaseImpl
(private val remoteRepository: RemoteRepository) : GetMealDetailsUseCase {
    override suspend fun invoke(id: Long): Result<MealDetails> =
        remoteRepository.getMealDetails(id)
}
