package mende273.foody.feature.random.di

import mende273.foody.feature.random.RandomMealViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val randomModule = module {
    viewModel { RandomMealViewModel(get(), get(), get(), get()) }
}