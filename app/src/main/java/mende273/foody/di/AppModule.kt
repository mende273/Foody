package mende273.foody.di

import org.koin.dsl.module

val appModule = module {
    includes(
        remoteDataSourceModule,
        localRepositoryModule,
        remoteRepositoryModule,
        viewModelModule,
        databaseModule
    )
}
