package mende273.foody.di

import mende273.foody.core.data.di.databaseModule
import org.koin.dsl.module

val appModule = module {
    includes(
        remoteDataSourceModule,
        localRepositoryModule,
        remoteRepositoryModule,
        viewModelModule,
        databaseModule,
        dispatchersModule
    )
}
