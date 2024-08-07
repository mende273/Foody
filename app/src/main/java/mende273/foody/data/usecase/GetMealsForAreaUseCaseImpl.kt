package mende273.foody.data.usecase

import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetMealsForAreaUseCase

class GetMealsForAreaUseCaseImpl(
    private val remoteRepository: RemoteRepository
) : GetMealsForAreaUseCase {
    override suspend fun invoke(area: String): Result<List<Meal>> =
        remoteRepository.getMealsForArea(area)
}
