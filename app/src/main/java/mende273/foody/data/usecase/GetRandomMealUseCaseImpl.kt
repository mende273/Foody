package mende273.foody.data.usecase

import mende273.foody.domain.model.MealDetails
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetRandomMealUseCase

class GetRandomMealUseCaseImpl(private val remoteRepository: RemoteRepository) :
    GetRandomMealUseCase {

    override suspend operator fun invoke(): Result<MealDetails> =
        remoteRepository.getRandomMeal()
}
