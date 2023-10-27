package mende273.foody.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse

class ApiService(private val client: HttpClient) {

    companion object {
        private const val ENDPOINT = "https://www.themealdb.com/api/json/v1/1/"
    }

    suspend fun getRandomMeal(): HttpResponse =
        client.get {
            url("${ENDPOINT}random.php")
        }

    suspend fun getMealsForCategory(category: String) =
        client.get {
            url("${ENDPOINT}filter.php")
            parameter("c", category)
        }

    suspend fun getMealsForArea(area: String) =
        client.get {
            url("${ENDPOINT}filter.php")
            parameter("a", area)
        }

    suspend fun getMealsForFirstLetter(letter: String) =
        client.get {
            url("${ENDPOINT}search.php")
            parameter("f", letter)
        }

    suspend fun getMealDetails(id: String) =
        client.get {
            url("${ENDPOINT}lookup.php")
            parameter("i", id)
        }

    suspend fun getMealCategories() =
        client.get {
            url("${ENDPOINT}list.php")
            parameter("c", "list")
        }

    suspend fun getMealAreas() =
        client.get {
            url("${ENDPOINT}list.php")
            parameter("a", "list")
        }
}
