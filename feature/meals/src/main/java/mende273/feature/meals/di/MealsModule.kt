package mende273.feature.meals.di

import mende273.feature.meals.MealsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mealsModule = module {
    viewModel { MealsViewModel(get(), get(), get(), get()) }
}