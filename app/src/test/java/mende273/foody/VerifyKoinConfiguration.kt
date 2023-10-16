package mende273.foody

import mende273.foody.di.appModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify

class VerifyKoinConfiguration {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkKoinModule() {
        // Verify Koin configuration
        appModule.verify(
            extraTypes = listOf(
                io.ktor.client.engine.HttpClientEngine::class,
                io.ktor.client.HttpClientConfig::class
            )
        )
    }
}
