package mende273.foody.data.usecase

import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.LocalRepository
import mende273.foody.domain.usecase.GetAllFavoriteMealsFromDBUseCase

class GetAllFavoriteMealsFromDBUseCaseImpl(
    private val localRepository: LocalRepository
) : GetAllFavoriteMealsFromDBUseCase {
    override suspend fun invoke(): List<Meal> =
        localRepository.getAllFavouriteMeals()
}
