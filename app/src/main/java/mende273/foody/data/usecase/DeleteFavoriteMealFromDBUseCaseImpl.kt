package mende273.foody.data.usecase

import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.LocalRepository
import mende273.foody.domain.usecase.DeleteFavoriteMealFromDBUseCase

class DeleteFavoriteMealFromDBUseCaseImpl(private val localRepository: LocalRepository) :
    DeleteFavoriteMealFromDBUseCase {

    override suspend fun invoke(meal: Meal) {
        localRepository.deleteFavouriteMeal(meal)
    }
}
