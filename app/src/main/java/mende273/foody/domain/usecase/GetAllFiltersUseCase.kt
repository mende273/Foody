package mende273.foody.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.mapper.toModel
import mende273.foody.util.exception.RetrieveNetworkDataException

class GetAllFiltersUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(): Flow<FiltersWrapper> {
        val categories: Result<List<String>> = remoteRepository.getMealCategories().fold(
            onSuccess = { mealCategoriesDto ->
                mealCategoriesDto?.let {
                    Result.success(it.toModel())
                } ?: Result.failure(RetrieveNetworkDataException())
            },
            onFailure = {
                Result.failure(it)
            }
        )

        val areas: Result<List<String>> = remoteRepository.getMealAreas().fold(
            onSuccess = { mealCategoriesDto ->
                mealCategoriesDto?.let {
                    Result.success(it.toModel())
                } ?: Result.failure(RetrieveNetworkDataException())
            },
            onFailure = {
                Result.failure(it)
            }
        )

        val firstLetters = getAlphabetLetters()

        return combine(
            flow { emit(categories) },
            flow { emit(areas) },
            flow { emit(firstLetters) }
        ) { c, a, f ->
            return@combine FiltersWrapper(c, a, f)
        }
    }
}

data class FiltersWrapper(
    val categories: Result<List<String>>,
    val areas: Result<List<String>>,
    val firstLetters: List<String>
)

private fun getAlphabetLetters(): List<String> {
    val alphabetLetters = mutableListOf<String>()
    var c = 'A'
    while (c <= 'Z') {
        alphabetLetters.add("$c")
        ++c
    }

    return alphabetLetters
}
