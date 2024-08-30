package mende273.foody.data.usecase.local

import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.LocalRepository
import mende273.foody.core.domain.usecase.local.DeleteFavoriteMealFromDBUseCase

class DeleteFavoriteMealFromDBUseCaseImpl(private val localRepository: LocalRepository) :
    DeleteFavoriteMealFromDBUseCase {

    override suspend fun invoke(meal: Meal) {
        localRepository.deleteFavouriteMeal(meal)
    }
}
