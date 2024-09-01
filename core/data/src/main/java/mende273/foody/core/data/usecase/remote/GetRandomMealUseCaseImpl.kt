package mende273.foody.core.data.usecase.remote

import mende273.foody.core.domain.model.MealDetails
import mende273.foody.core.domain.repository.RemoteRepository
import mende273.foody.core.domain.usecase.remote.GetRandomMealUseCase

class GetRandomMealUseCaseImpl(private val remoteRepository: RemoteRepository) :
    GetRandomMealUseCase {

    override suspend operator fun invoke(): Result<MealDetails> =
        remoteRepository.getRandomMeal()
}
