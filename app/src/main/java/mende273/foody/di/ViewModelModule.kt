package mende273.foody.di

import mende273.foody.ui.screen.details.MealDetailsViewModel
import mende273.foody.ui.screen.favourites.FavouritesViewModel
import mende273.foody.ui.screen.filter.area.FilterMealsByAreaViewModel
import mende273.foody.ui.screen.filter.category.FilterMealsByCategoryViewModel
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
    viewModel { FilterMealsByCategoryViewModel(get()) }
    viewModel { FilterMealsByAreaViewModel(get()) }
    viewModel { MealDetailsViewModel(get()) }
}
