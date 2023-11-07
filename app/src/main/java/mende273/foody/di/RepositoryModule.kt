package mende273.foody.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import mende273.foody.data.api.ApiService
import mende273.foody.data.repository.LocalRepositoryImpl
import mende273.foody.data.repository.RemoteRepositoryImpl
import mende273.foody.domain.usecase.GetAllFiltersUseCase
import mende273.foody.domain.usecase.GetMealDetailsUseCase
import mende273.foody.domain.usecase.GetMealsForAreaUseCase
import mende273.foody.domain.usecase.GetMealsForCategoryUseCase
import mende273.foody.domain.usecase.GetMealsForFirstLetterUseCase
import mende273.foody.domain.usecase.GetMealsWithIngredientUseCase
import mende273.foody.domain.usecase.GetRandomMealUseCase
import mende273.foody.domain.usecase.SearchMealsByNameUseCase
import org.koin.dsl.module

val repositoryModule = module {
    single { ApiService(provideHttpClient()) }
    single { RemoteRepositoryImpl(get()) }
    single { LocalRepositoryImpl(get()) }
    factory { GetRandomMealUseCase(get()) }
    factory { GetMealsForCategoryUseCase(get()) }
    factory { GetMealsForAreaUseCase(get()) }
    factory { GetMealsForFirstLetterUseCase(get()) }
    factory { GetMealDetailsUseCase(get()) }
    factory { GetMealsWithIngredientUseCase(get()) }
    factory { SearchMealsByNameUseCase(get()) }
    single { GetAllFiltersUseCase(get()) }
}

private const val NETWORK_TIME_OUT = 6_000L

fun provideHttpClient(): HttpClient = HttpClient(Android) {
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

    install(HttpTimeout) {
        requestTimeoutMillis = NETWORK_TIME_OUT
        connectTimeoutMillis = NETWORK_TIME_OUT
        socketTimeoutMillis = NETWORK_TIME_OUT
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
        contentType(ContentType.Application.Json)
        accept(ContentType.Application.Json)
    }
}
