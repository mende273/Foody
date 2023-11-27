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

            val actualResult = response.getOrNull()

            assertEquals(52999, actualResult?.id)
            assertEquals("Crispy Sausages and Greens", actualResult?.name)
            assertEquals("Pork", actualResult?.category)
            assertEquals("Irish", actualResult?.area)
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

            val actualList = response.getOrNull()

            val expectedListSize = 8

            assertEquals(expectedListSize, actualList?.size)
        }
    }

    @Test
    fun getMealsWithIngredient() {
        // TODO
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
