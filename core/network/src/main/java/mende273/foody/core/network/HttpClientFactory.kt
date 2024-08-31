package mende273.foody.core.network

import io.ktor.client.HttpClient

interface HttpClientFactory {
    fun create(): HttpClient
}
