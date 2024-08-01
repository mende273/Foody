package mende273.foody.data.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import mende273.foody.domain.model.FiltersWrapper
import mende273.foody.domain.usecase.GetAllFiltersUseCase
import mende273.foody.domain.usecase.GetMealAreasUseCase
import mende273.foody.domain.usecase.GetMealCategoriesUseCase

class GetAllFiltersUseCaseImpl(
    private val getMealCategories: GetMealCategoriesUseCase,
    private val getMealAreas: GetMealAreasUseCase
) :
    GetAllFiltersUseCase {

    override suspend operator fun invoke(): FiltersWrapper =
        withContext(Dispatchers.IO) {
            val categories = async { getMealCategories() }
            val areas = async { getMealAreas() }
            val firstLetters = ('A'..'Z').toList().map { it.toString() }

            FiltersWrapper(categories.await(), areas.await(), firstLetters)
        }
}
