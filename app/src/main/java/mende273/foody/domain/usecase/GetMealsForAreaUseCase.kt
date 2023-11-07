package mende273.foody.domain.usecase

import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.mapper.toDomain
import mende273.foody.domain.model.Meal

class GetMealsForAreaUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(area: String): Result<List<Meal>> {
        return remoteRepository.getMealsForArea(area).fold(
            onSuccess = { Result.success(it.toDomain()) },
            onFailure = { Result.failure(it) }
        )
    }
}
