package mende273.foody.data.repository.local

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import mende273.foody.data.mapper.toDomainModel
import mende273.foody.data.mapper.toEntityModel
import mende273.foody.data.source.local.LocalDataSource
import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.LocalRepository

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

    override suspend fun getFavouriteMealById(id: Long): Flow<Meal?> =
        withContext(ioDispatcher) {
            localDataSource
                .selectById(id)
                .map { it?.toDomainModel() }
        }

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
