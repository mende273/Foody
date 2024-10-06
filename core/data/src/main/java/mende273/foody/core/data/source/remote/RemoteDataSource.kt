package mende273.foody.core.data.source.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse

class RemoteDataSource(private val client: HttpClient) {

    suspend fun getRandomMeal(): HttpResponse =
        client.get {
            url("random.php")
        }

    suspend fun getMealsForCategory(category: String) =
        client.get {
            url("filter.php")
            parameter("c", category)
        }

    suspend fun getMealsForArea(area: String) =
        client.get {
            url("filter.php")
            parameter("a", area)
        }

    suspend fun getMealsForFirstLetter(letter: String) =
        client.get {
            url("search.php")
            parameter("f", letter)
        }

    suspend fun getMealsWithIngredient(ingredient: String) =
        client.get {
            url("filter.php")
            parameter("i", ingredient)
        }

    suspend fun getMealDetails(id: Long) =
        client.get {
            url("lookup.php")
            parameter("i", id)
        }

    suspend fun getMealCategories() =
        client.get {
            url("list.php")
            parameter("c", "list")
        }

    suspend fun getMealAreas() =
        client.get {
            url("list.php")
            parameter("a", "list")
        }

    suspend fun searchMealsByName(name: String) =
        client.get {
            url("search.php")
            parameter("s", name)
        }
}
