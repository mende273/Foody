package mende273.foody.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import mende273.foody.domain.repository.RemoteRepository

class GetAllFiltersUseCase(private val remoteRepository: RemoteRepository) {

    suspend operator fun invoke(): Flow<FiltersWrapper> {
        val categories: Result<List<String>> = remoteRepository.getMealCategories()
        val areas: Result<List<String>> = remoteRepository.getMealAreas()
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
