package mende273.foody.core.data.usecase.local

import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.LocalRepository
import mende273.foody.core.domain.usecase.local.AddFavoriteMealToDBUseCase

class AddFavoriteMealToDBUseCaseImpl(private val localRepository: LocalRepository) :
    AddFavoriteMealToDBUseCase {
    override suspend fun invoke(meal: Meal) {
        localRepository.addFavouriteMeal(meal)
    }
}
