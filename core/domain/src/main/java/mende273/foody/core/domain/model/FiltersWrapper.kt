package mende273.foody.core.domain.model

data class FiltersWrapper(
    val categories: Result<List<String>>,
    val areas: Result<List<String>>,
    val firstLetters: List<String>
)
