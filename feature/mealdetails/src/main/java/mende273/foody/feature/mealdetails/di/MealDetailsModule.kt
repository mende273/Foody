package mende273.foody.feature.mealdetails.di

import mende273.foody.feature.mealdetails.MealDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mealDetailsModule = module {
    viewModel { MealDetailsViewModel(get(), get(), get(), get()) }
}