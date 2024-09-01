package mende273.foody.core.data.usecase.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import mende273.foody.core.domain.model.FiltersWrapper
import mende273.foody.core.domain.usecase.remote.GetAllFiltersUseCase
import mende273.foody.core.domain.usecase.remote.GetMealAreasUseCase
import mende273.foody.core.domain.usecase.remote.GetMealCategoriesUseCase

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
