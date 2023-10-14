package dev.mende273.foody.di

import android.util.Log
import dev.mende273.foody.data.api.ApiService
import dev.mende273.foody.data.repository.RemoteRepositoryImpl
import dev.mende273.foody.domain.usecase.GetRandomMealUseCase
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val repositoryModule = module {
    single { ApiService(provideHttpClient()) }
    single { RemoteRepositoryImpl(get()) }
    factory { GetRandomMealUseCase(get()) }
}

const val NETWORK_TIME_OUT = 6_000

fun provideHttpClient(): HttpClient = HttpClient(Android) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )

        engine {
            connectTimeout = NETWORK_TIME_OUT
            socketTimeout = NETWORK_TIME_OUT
        }
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor =>", message)
            }
        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("HTTP status:", "${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }

    defaultRequest {
        if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
    }
}
