package mende273.foody.data.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import mende273.foody.data.dto.MealsWithDetailsDto

class ApiService(private val client: HttpClient) {

    companion object {
        private const val ENDPOINT = "https://www.themealdb.com/api/json/v1/1/"
    }

    suspend fun getRandomMeal(): MealsWithDetailsDto =
        client.get {
            url("${ENDPOINT}random.php")
        }
}
