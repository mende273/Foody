package mende273.foody.core.domain.usecase.local

import kotlinx.coroutines.flow.Flow
import mende273.foody.core.domain.model.Meal

fun interface GetFavoriteMealByIdFromDBUseCase {
    suspend operator fun invoke(id: Long): Flow<Meal?>
}
