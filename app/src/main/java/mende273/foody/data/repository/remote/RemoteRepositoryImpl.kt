package mende273.foody.data.repository.remote

import io.ktor.client.call.body
import mende273.foody.data.dto.MealCategoriesDto
import mende273.foody.data.dto.MealsDto
import mende273.foody.data.dto.MealsWithDetailsDto
import mende273.foody.data.mapper.toModel
import mende273.foody.data.source.remote.RemoteDataSource
import mende273.foody.domain.model.Meal
import mende273.foody.domain.model.MealDetails
import mende273.foody.domain.repository.RemoteRepository

class RemoteRepositoryImpl(private val remoteDataSource: RemoteDataSource) : RemoteRepository {

    override suspend fun getRandomMeal(): Result<MealDetails> =
        runCatching {
            remoteDataSource.getRandomMeal().body<MealsWithDetailsDto?>().toModel().first()
        }

    override suspend fun getMealsForCategory(category: String): Result<List<Meal>> =
        runCatching {
            remoteDataSource.getMealsForCategory(category).body<MealsDto?>().toModel()
        }

    override suspend fun getMealsForArea(area: String): Result<List<Meal>> =
        runCatching {
            remoteDataSource.getMealsForArea(area).body<MealsDto?>().toModel()
        }

    override suspend fun getMealsWithIngredient(ingredient: String): Result<List<Meal>> =
        runCatching {
            remoteDataSource.getMealsWithIngredient((ingredient)).body<MealsDto?>().toModel()
        }

    override suspend fun getMealsForFirstLetter(letter: String): Result<List<Meal>> =
        runCatching {
            remoteDataSource.getMealsForFirstLetter(letter).body<MealsDto?>().toModel()
        }

    override suspend fun getMealDetails(id: String): Result<MealDetails> =
        runCatching {
            remoteDataSource.getMealDetails(id).body<MealsWithDetailsDto?>().toModel().first()
        }

    override suspend fun getMealCategories(): Result<List<String>> =
        runCatching {
            remoteDataSource.getMealCategories().body<MealCategoriesDto?>().toModel()
        }

    override suspend fun getMealAreas(): Result<List<String>> =
        runCatching {
            remoteDataSource.getMealAreas().body<MealCategoriesDto?>().toModel()
        }

    override suspend fun searchMealsByName(name: String): Result<List<Meal>> =
        runCatching { remoteDataSource.searchMealsByName(name).body<MealsDto?>().toModel() }
}
