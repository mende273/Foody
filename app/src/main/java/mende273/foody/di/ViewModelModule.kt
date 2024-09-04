package mende273.foody.di

import mende273.foody.feature.filtermeals.FilterMealsViewModel
import mende273.foody.feature.mealdetails.MealDetailsViewModel
import mende273.foody.feature.meals.MealsViewModel
import mende273.foody.feature.random.RandomMealViewModel
import mende273.foody.feature.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MealsViewModel(get(), get(), get(), get()) }
    viewModel { RandomMealViewModel(get(), get(), get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { FilterMealsViewModel(get(), get(), get()) }
    viewModel { MealDetailsViewModel(get(), get(), get(), get()) }
}
