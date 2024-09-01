package mende273.foody.di

import mende273.foody.core.data.source.remote.RemoteDataSource
import mende273.foody.core.network.KtorHttpClient
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(KtorHttpClient().create()) }
}
