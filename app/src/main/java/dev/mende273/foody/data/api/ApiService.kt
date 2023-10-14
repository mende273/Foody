package dev.mende273.foody.data.api

import dev.mende273.foody.data.dto.MealsDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class ApiService(private val client: HttpClient) {

    companion object {
        private const val ENDPOINT = "https://www.themealdb.com/api/json/v1/1/"
    }

    suspend fun getRandomMeal(): MealsDto =
        client.get {
            url("${ENDPOINT}random.php")
        }
}
