package mende273.foody.core.data.repository.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import mende273.foody.core.data.mapper.toDomainModel
import mende273.foody.core.data.mapper.toEntityModel
import mende273.foody.core.data.source.local.LocalDataSource
import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.repository.LocalRepository

class LocalRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : LocalRepository {

    override suspend fun getAllFavouriteMeals(): List<Meal> =
        withContext(ioDispatcher) {
            localDataSource
                .selectAll()
                .toDomainModel()
        }

    override fun getFavouriteMealById(id: Long): Flow<Meal?> =
        localDataSource.selectById(id).map { it?.toDomainModel() }

    override suspend fun addFavouriteMeal(meal: Meal) {
        withContext(ioDispatcher) {
            localDataSource.insert(meal.toEntityModel())
        }
    }

    override suspend fun deleteFavouriteMeal(meal: Meal) {
        withContext(ioDispatcher) {
            localDataSource.delete(meal.id)
        }
    }
}
