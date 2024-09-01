package mende273.foody.core.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MockHttpClient {
    fun get() = client

    private val responseHeaders =
        headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

    private val client = HttpClient(MockEngine) {
        engine {
            addHandler {
                val mockResponse = getMockResponseForRequest(it)
                if (mockResponse.isNotEmpty()) {
                    respond(
                        content = mockResponse,
                        headers = responseHeaders,
                        status = HttpStatusCode.OK
                    )
                } else {
                    error("Unhandled ${it.url.encodedPath}")
                }
            }
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    useAlternativeNames = true
                    ignoreUnknownKeys = true
                    encodeDefaults = false
                }
            )
        }
    }
}
