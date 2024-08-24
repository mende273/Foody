package mende273.foody.data.usecase.local

import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.LocalRepository
import mende273.foody.domain.usecase.local.AddFavoriteMealToDBUseCase

class AddFavoriteMealToDBUseCaseImpl(private val localRepository: LocalRepository) :
    AddFavoriteMealToDBUseCase {
    override suspend fun invoke(meal: Meal) {
        localRepository.addFavouriteMeal(meal)
    }
}
