package mende273.foody.di

import mende273.foody.data.repository.local.LocalRepositoryImpl
import mende273.foody.domain.repository.LocalRepository
import org.koin.dsl.module

val localRepositoryModule = module {
    single<LocalRepository> { LocalRepositoryImpl(get()) }
}
