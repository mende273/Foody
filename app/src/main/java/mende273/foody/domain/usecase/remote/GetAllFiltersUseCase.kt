package mende273.foody.domain.usecase.remote

import mende273.foody.domain.model.FiltersWrapper

fun interface GetAllFiltersUseCase {
    suspend operator fun invoke(): FiltersWrapper
}
