package dev.mende273.foody.di

import org.koin.dsl.module

val appModule = module {
    includes(viewModelModule, repositoryModule)
}
