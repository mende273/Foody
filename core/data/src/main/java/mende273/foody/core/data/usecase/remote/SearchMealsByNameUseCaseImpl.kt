package mende273.foody.core.data.usecase.remote

import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.RemoteRepository
import mende273.foody.core.domain.usecase.remote.SearchMealsByNameUseCase

class SearchMealsByNameUseCaseImpl
(private val remoteRepository: RemoteRepository) : SearchMealsByNameUseCase {
    override suspend fun invoke(name: String): Result<List<Meal>> =
        remoteRepository.searchMealsByName(name)
}
