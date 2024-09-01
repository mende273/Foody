package mende273.foody.core.domain.usecase.remote

import mende273.foody.core.domain.model.FiltersWrapper

fun interface GetAllFiltersUseCase {
    suspend operator fun invoke(): FiltersWrapper
}
