package mende273.foody.core.data.source.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import mende273.foody.MealEntity
import mende273.foody.core.data.Database

class LocalDataSource(private val database: Database) {
    private val query get() = database.mealQueries

    fun selectAll(): List<MealEntity> = query.selectAll().executeAsList()

    fun insert(mealEntity: MealEntity) {
        query.insert(mealEntity)
    }

    fun selectById(id: Long): Flow<MealEntity?> =
        query.selectById(id).asFlow().mapToOneOrNull(Dispatchers.IO)

    fun delete(id: Long) {
        query.delete(id)
    }
}
