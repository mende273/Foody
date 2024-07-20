package mende273.foody.domain.usecase

import kotlinx.coroutines.flow.Flow
import mende273.foody.domain.model.FiltersWrapper

fun interface GetAllFiltersUseCase {
    suspend operator fun invoke(): Flow<FiltersWrapper>
}
