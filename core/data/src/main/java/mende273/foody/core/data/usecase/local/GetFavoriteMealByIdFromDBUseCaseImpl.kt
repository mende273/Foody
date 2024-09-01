package mende273.foody.core.data.usecase.local

import kotlinx.coroutines.flow.Flow
import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.LocalRepository
import mende273.foody.core.domain.usecase.local.GetFavoriteMealByIdFromDBUseCase

class GetFavoriteMealByIdFromDBUseCaseImpl
(private val localRepository: LocalRepository) : GetFavoriteMealByIdFromDBUseCase {

    override suspend fun invoke(id: Long): Flow<Meal?> =
        localRepository.getFavouriteMealById(id)
}
