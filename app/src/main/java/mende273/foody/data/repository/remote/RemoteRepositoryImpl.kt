package mende273.foody.data.repository.remote

import io.ktor.client.call.body
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import mende273.foody.data.dto.MealCategoriesDto
import mende273.foody.data.dto.MealsDto
import mende273.foody.data.dto.MealsWithDetailsDto
import mende273.foody.data.mapper.toDomainModel
import mende273.foody.data.source.remote.RemoteDataSource
import mende273.foody.domain.model.Meal
import mende273.foody.domain.model.MealDetails
import mende273.foody.domain.repository.RemoteRepository

class RemoteRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : RemoteRepository {

    override suspend fun getRandomMeal(): Result<MealDetails> =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getRandomMeal()
                    .body<MealsWithDetailsDto?>()
                    .toDomainModel()
                    .first()
            }
        }

    override suspend fun getMealsForCategory(category: String): Result<List<Meal>> =
        runCatching {
            remoteDataSource
                .getMealsForCategory(category)
                .body<MealsDto?>()
                .toDomainModel()
        }

    override suspend fun getMealsForArea(area: String): Result<List<Meal>> =
        runCatching {
            remoteDataSource
                .getMealsForArea(area)
                .body<MealsDto?>()
                .toDomainModel()
        }

    override suspend fun getMealsWithIngredient(ingredient: String): Result<List<Meal>> =
        runCatching {
            remoteDataSource
                .getMealsWithIngredient((ingredient))
                .body<MealsDto?>()
                .toDomainModel()
        }

    override suspend fun getMealsForFirstLetter(letter: String): Result<List<Meal>> =
        runCatching {
            remoteDataSource
                .getMealsForFirstLetter(letter)
                .body<MealsDto?>()
                .toDomainModel()
        }

    override suspend fun getMealDetails(id: Long): Result<MealDetails> =
        runCatching {
            remoteDataSource
                .getMealDetails(id)
                .body<MealsWithDetailsDto?>()
                .toDomainModel()
                .first()
        }

    override suspend fun getMealCategories(): Result<List<String>> =
        runCatching {
            remoteDataSource
                .getMealCategories()
                .body<MealCategoriesDto?>()
                .toDomainModel()
        }

    override suspend fun getMealAreas(): Result<List<String>> =
        runCatching {
            remoteDataSource
                .getMealAreas()
                .body<MealCategoriesDto?>()
                .toDomainModel()
        }

    override suspend fun searchMealsByName(name: String): Result<List<Meal>> =
        runCatching {
            remoteDataSource
                .searchMealsByName(name)
                .body<MealsDto?>()
                .toDomainModel()
        }
}
