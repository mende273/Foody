package mende273.foody

import mende273.foody.core.data.di.databaseModule
import mende273.foody.core.data.di.dispatchersModule
import mende273.foody.core.data.di.localRepositoryModule
import mende273.foody.core.data.di.remoteDataSourceModule
import mende273.foody.core.data.di.remoteRepositoryModule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

class VerifyKoinConfiguration {

    @Test
    fun checkKoinModule() {
        koinApplication {
            androidContext(FoodyApplication())
            modules(
                databaseModule,
                dispatchersModule,
                localRepositoryModule,
                remoteDataSourceModule,
                remoteRepositoryModule
            )
            checkModules()
        }
    }
}
