package mende273.foody.feature.filtermeals.di

import mende273.foody.feature.filtermeals.FilterMealsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val filterMealsModule = module {
    viewModel { FilterMealsViewModel(get(), get(), get()) }
}