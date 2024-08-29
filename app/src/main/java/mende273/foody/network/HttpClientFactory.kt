package mende273.foody.network

import io.ktor.client.HttpClient

interface HttpClientFactory {
    fun create(): HttpClient
}
