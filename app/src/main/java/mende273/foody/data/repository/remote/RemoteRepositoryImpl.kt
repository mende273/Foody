package mende273.foody.data.repository.remote

import io.ktor.client.call.body
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import mende273.foody.core.data.dto.MealCategoriesDto
import mende273.foody.core.data.dto.MealsDto
import mende273.foody.core.data.dto.MealsWithDetailsDto
import mende273.foody.core.data.source.remote.RemoteDataSource
import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.model.MealDetails
import mende273.foody.core.domain.repository.RemoteRepository
import mende273.foody.data.mapper.toDomainModel

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
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getMealsForCategory(category)
                    .body<MealsDto?>()
                    .toDomainModel()
            }
        }

    override suspend fun getMealsForArea(area: String): Result<List<Meal>> =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getMealsForArea(area)
                    .body<MealsDto?>()
                    .toDomainModel()
            }
        }

    override suspend fun getMealsWithIngredient(ingredient: String): Result<List<Meal>> =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getMealsWithIngredient((ingredient))
                    .body<MealsDto?>()
                    .toDomainModel()
            }
        }

    override suspend fun getMealsForFirstLetter(letter: String): Result<List<Meal>> =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getMealsForFirstLetter(letter)
                    .body<MealsDto?>()
                    .toDomainModel()
            }
        }

    override suspend fun getMealDetails(id: Long): Result<MealDetails> =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getMealDetails(id)
                    .body<MealsWithDetailsDto?>()
                    .toDomainModel()
                    .first()
            }
        }

    override suspend fun getMealCategories(): Result<List<String>> =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getMealCategories()
                    .body<MealCategoriesDto?>()
                    .toDomainModel()
            }
        }

    override suspend fun getMealAreas(): Result<List<String>> =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .getMealAreas()
                    .body<MealCategoriesDto?>()
                    .toDomainModel()
            }
        }

    override suspend fun searchMealsByName(name: String): Result<List<Meal>> =
        withContext(ioDispatcher) {
            runCatching {
                remoteDataSource
                    .searchMealsByName(name)
                    .body<MealsDto?>()
                    .toDomainModel()
            }
        }
}
