package mende273.foody.di

import mende273.foody.data.network.KtorHttpClient
import mende273.foody.data.source.remote.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { RemoteDataSource(KtorHttpClient().create()) }
}
