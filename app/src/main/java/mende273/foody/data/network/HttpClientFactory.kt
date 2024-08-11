package mende273.foody.data.network

import io.ktor.client.HttpClient

interface HttpClientFactory {
    fun create(): HttpClient
}
