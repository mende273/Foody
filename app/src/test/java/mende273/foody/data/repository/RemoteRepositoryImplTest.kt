package mende273.foody.data.repository

import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import mende273.foody.data.api.ApiService
import org.junit.Test

class RemoteRepositoryImplTest {

    private val client = MockHttpClient()
    private val apiService = ApiService(client.get())
    private val remoteRepositoryImpl = RemoteRepositoryImpl(apiService)

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
    fun getMealsForCategory() =
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.getMealsForCategory("breakfast")

            assertTrue { response.isSuccess }

            val actualList = response.getOrNull()

            val expectedListSize = 8

            assertEquals(expectedListSize, actualList?.size)
        }

    @Test
    fun getMealsForArea() {
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.getMealsForArea("croatian")

            assertTrue { response.isSuccess }

            val actualListSize = response.getOrNull()?.size ?: 0

            val expectedListSize = 8

            assertEquals(expectedListSize, actualListSize)
        }
    }

    @Test
    fun getMealsWithIngredient() {
        runTest(StandardTestDispatcher()) {
            val response = remoteRepositoryImpl.getMealsWithIngredient("chicken_breast")

            assertTrue { response.isSuccess }

            val actualListSize = response.getOrNull()?.size ?: 0
            val expectedListSize = 9

            assertEquals(expectedListSize, actualListSize)
        }
    }

    @Test
    fun getMealsForFirstLetter() {
        // TODO
    }

    @Test
    fun getMealDetails() {
        // TODO
    }

    @Test
    fun getMealCategories() {
        // TODO
    }

    @Test
    fun getMealAreas() {
        // TODO
    }

    @Test
    fun searchMealsByName() {
        // TODO
    }
}
