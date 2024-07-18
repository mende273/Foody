package mende273.foody.di

import mende273.foody.ui.screen.favourites.FavouritesViewModel
import mende273.foody.ui.screen.meals.MealsViewModel
import mende273.foody.ui.screen.meals.details.MealDetailsViewModel
import mende273.foody.ui.screen.meals.filter.FilterMealsViewModel
import mende273.foody.ui.screen.random.RandomMealViewModel
import mende273.foody.ui.screen.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MealsViewModel(get(), get()) }
    viewModel { RandomMealViewModel(get(), get(), get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { FavouritesViewModel(get()) }
    viewModel { FilterMealsViewModel(get()) }
    viewModel { MealDetailsViewModel(get(), get(), get(), get()) }
}
