package mende273.foody.data.usecase.remote

import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.RemoteRepository
import mende273.foody.core.domain.usecase.remote.GetMealsForAreaUseCase

class GetMealsForAreaUseCaseImpl(
    private val remoteRepository: RemoteRepository
) : GetMealsForAreaUseCase {
    override suspend fun invoke(area: String): Result<List<Meal>> =
        remoteRepository.getMealsForArea(area)
}
