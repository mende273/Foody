package mende273.foody.core.data.usecase.local

import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.LocalRepository
import mende273.foody.core.domain.usecase.local.GetAllFavoriteMealsFromDBUseCase

class GetAllFavoriteMealsFromDBUseCaseImpl(
    private val localRepository: LocalRepository
) : GetAllFavoriteMealsFromDBUseCase {
    override suspend fun invoke(): List<Meal> =
        localRepository.getAllFavouriteMeals()
}
