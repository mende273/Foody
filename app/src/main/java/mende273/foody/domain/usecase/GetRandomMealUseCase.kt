package mende273.foody.domain.usecase

import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.model.Meal
import mende273.foody.mapper.toDomain
import mende273.foody.util.exception.RetrieveNetworkDataException

class GetRandomMealUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(): Result<Meal> {
        return remoteRepository.getRandomMeal().fold(
            onSuccess = { mealsDto ->
                val meal: Meal? = mealsDto?.takeIf { it.meals != null }
                    ?.toDomain()
                    ?.meals?.get(0)

                meal?.let {
                    Result.success(it)
                } ?: Result.failure(RetrieveNetworkDataException())
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }
}
