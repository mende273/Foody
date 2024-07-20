package mende273.foody.data.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import mende273.foody.domain.model.FiltersWrapper
import mende273.foody.domain.usecase.GetAllFiltersUseCase
import mende273.foody.domain.usecase.GetMealAreasUseCase
import mende273.foody.domain.usecase.GetMealCategoriesUseCase

class GetAllFiltersUseCaseImpl(
    private val getMealCategories: GetMealCategoriesUseCase,
    private val getMealAreas: GetMealAreasUseCase
) :
    GetAllFiltersUseCase {

    override suspend operator fun invoke(): Flow<FiltersWrapper> {
        val categories: Result<List<String>> = getMealCategories()
        val areas: Result<List<String>> = getMealAreas()
        val firstLetters = ('A'..'Z').toList().map { it.toString() }

        return combine(
            flow { emit(categories) },
            flow { emit(areas) },
            flow { emit(firstLetters) }
        ) { c, a, f ->
            return@combine FiltersWrapper(c, a, f)
        }
    }
}
