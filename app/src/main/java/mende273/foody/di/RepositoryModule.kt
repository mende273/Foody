package mende273.foody.di

import mende273.foody.data.repository.remote.RemoteRepositoryImpl
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetAllFiltersUseCase
import org.koin.dsl.module

val remoteRepositoryModule = module {
    single<RemoteRepository> { RemoteRepositoryImpl(get()) }
    single { GetAllFiltersUseCase(get()) }
}
