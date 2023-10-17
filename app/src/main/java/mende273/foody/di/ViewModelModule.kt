package mende273.foody.di

import mende273.foody.ui.screen.favourites.FavouritesViewModel
import mende273.foody.ui.screen.filter.category.FilterMealByCategoryViewModel
import mende273.foody.ui.screen.meals.MealsViewModel
import mende273.foody.ui.screen.random.RandomMealViewModel
import mende273.foody.ui.screen.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MealsViewModel() }
    viewModel { RandomMealViewModel(get()) }
    viewModel { SearchViewModel() }
    viewModel { FavouritesViewModel() }
    viewModel { FilterMealByCategoryViewModel() }
}
