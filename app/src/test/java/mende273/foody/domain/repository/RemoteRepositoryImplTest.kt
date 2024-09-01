package mende273.foody.domain.repository

import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import mende273.foody.core.data.repository.remote.RemoteRepositoryImpl
import mende273.foody.core.data.source.remote.RemoteDataSource
import org.junit.Test

class RemoteRepositoryImplTest {

    private val client = MockHttpClient()
    private val remoteDataSource = RemoteDataSource(client.get())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val remoteRepositoryImpl = RemoteRepositoryImpl(remoteDataSource, testDispatcher)

    @Test
    fun `getRandomMeal() should return a MealDetails object`() =
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.getRandomMeal()

            assertTrue { response.isSuccess }

            val expectedMealDetails = provideMockMealDetailsObject()
            val actualMealDetails = response.getOrNull()

            assertEquals(expectedMealDetails, actualMealDetails)
        }

    @Test
    fun `getMealsForCategory should return list of Meal objects`() =
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.getMealsForCategory("breakfast")

            assertTrue { response.isSuccess }

            val actualList = response.getOrNull()

            val expectedListSize = 8

            assertEquals(expectedListSize, actualList?.size)
        }

    @Test
    fun `getMealsForArea should return list of Meal objects`() =
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.getMealsForArea("croatian")

            assertTrue { response.isSuccess }

            val actualListSize = response.getOrNull()?.size ?: 0

            val expectedListSize = 8

            assertEquals(expectedListSize, actualListSize)
        }

    @Test
    fun `getMealsWithIngredient should return list of Meal objects`() =
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.getMealsWithIngredient("chicken_breast")

            assertTrue { response.isSuccess }

            val actualListSize = response.getOrNull()?.size ?: 0
            val expectedListSize = 9

            assertEquals(expectedListSize, actualListSize)
        }

    @Test
    fun `getMealsForFirstLetter should return  list of Meal objects`() =
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.getMealsForFirstLetter("y")

            assertTrue { response.isSuccess }

            val actualListSize = response.getOrNull()?.size ?: 0
            val expectedListSize = 1

            assertEquals(expectedListSize, actualListSize)
        }

    @Test
    fun `getMealDetails should return MealDetails object`() = runTest(StandardTestDispatcher()) {
        val response = remoteRepositoryImpl.getMealDetails(52999)

        assertTrue { response.isSuccess }

        val actualMealDetails = response.getOrNull()
        val expectedMealDetails = provideMockMealDetailsObject()

        assertEquals(expectedMealDetails, actualMealDetails)
    }

    @Test
    fun `getMealCategories should return list of category items`() =
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.getMealCategories()

            assertTrue { response.isSuccess }

            val actualMealCategories = response.getOrNull()
            val expectedMealCategories = provideMockCategories()

            assertEquals(expectedMealCategories, actualMealCategories)
        }

    @Test
    fun `getMealAreas should return list of area items`() = runTest(StandardTestDispatcher()) {
        val response = remoteRepositoryImpl.getMealAreas()

        assertTrue { response.isSuccess }

        val actualCategories = response.getOrNull()
        val expectedCategories = provideMockAreas()

        assertEquals(expectedCategories, actualCategories)
    }

    @Test
    fun `searchMealsByName should return list of Meal objects`() =
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.searchMealsByName("Arrabiata")

            assertTrue { response.isSuccess }

            val actualListSize = response.getOrNull()?.size ?: 0
            val expectedListSize = 1

            assertEquals(expectedListSize, actualListSize)
        }
}
