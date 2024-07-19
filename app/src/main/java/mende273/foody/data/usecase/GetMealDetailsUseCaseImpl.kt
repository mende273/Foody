package mende273.foody.data.usecase

import mende273.foody.domain.model.MealDetails
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetMealDetailsUseCase

class GetMealDetailsUseCaseImpl
(private val remoteRepository: RemoteRepository) : GetMealDetailsUseCase {
    override suspend fun invoke(id: Long): Result<MealDetails> {
        return remoteRepository.getMealDetails(id)
    }
}
