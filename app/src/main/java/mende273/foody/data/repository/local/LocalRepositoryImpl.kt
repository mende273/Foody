package mende273.foody.data.repository.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import mende273.foody.MealEntity
import mende273.foody.data.dao.MealDao
import mende273.foody.domain.model.Meal
import mende273.foody.domain.repository.LocalRepository

class LocalRepositoryImpl(private val mealDao: MealDao) : LocalRepository {

    override suspend fun getAllFavouriteMeals(): List<Meal> =
        mealDao.selectAll().map {
            Meal(it.id, it.name, it.thumb)
        }

    override suspend fun getFavouriteMealById(id: Long): Flow<Meal?> {
        return mealDao.selectById(id).map { mealEntity ->
            mealEntity?.let {
                Meal(it.id, it.name, it.thumb)
            }
        }
    }

    override suspend fun addFavouriteMeal(meal: Meal) {
        mealDao.insert(MealEntity(meal.id, meal.name, meal.thumb))
    }

    override suspend fun deleteFavouriteMeal(meal: Meal) {
        mealDao.delete(meal.id)
    }
}
