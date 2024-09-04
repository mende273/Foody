package mende273.foody.feature.favorites.di

import mende273.foody.feature.favorites.FavouritesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritesModule = module {
    viewModel { FavouritesViewModel(get()) }
}