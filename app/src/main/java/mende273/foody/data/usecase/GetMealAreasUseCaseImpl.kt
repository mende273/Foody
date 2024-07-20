package mende273.foody.data.usecase

import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetMealAreasUseCase

class GetMealAreasUseCaseImpl(
    private val remoteRepository: RemoteRepository
) : GetMealAreasUseCase {
    override suspend fun invoke(): Result<List<String>> =
        remoteRepository.getMealAreas()
}
