package mende273.foody.domain.usecase

import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.model.Meals
import mende273.foody.domain.mapper.toDomain
import mende273.foody.util.exception.RetrieveNetworkDataException

class GetMealsForCategoryUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(category: String): Result<Meals> {
        return remoteRepository.getMealsForCategory(category).fold(
            onSuccess = { mealsDto ->
                val meals: Meals? = mealsDto
                    ?.takeIf { it.meals != null }
                    ?.toDomain()

                meals?.let {
                    Result.success(it)
                } ?: Result.failure(RetrieveNetworkDataException())
            },
            onFailure = {
                Result.failure(it)
            }
        )
    }
}
