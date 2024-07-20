package mende273.foody.data.usecase

import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.SearchMealsByNameUseCase

class SearchMealsByNameUseCaseImpl
(private val remoteRepository: RemoteRepository) : SearchMealsByNameUseCase {
    override suspend fun invoke(name: String): Result<List<Meal>> =
        remoteRepository.searchMealsByName(name)
}
