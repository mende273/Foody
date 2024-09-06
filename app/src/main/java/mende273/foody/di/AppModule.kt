package mende273.foody.di

import mende273.feature.meals.di.mealsModule
import mende273.foody.core.data.di.databaseModule
import mende273.foody.core.data.di.dispatchersModule
import mende273.foody.core.data.di.localRepositoryModule
import mende273.foody.core.data.di.remoteDataSourceModule
import mende273.foody.core.data.di.remoteRepositoryModule
import mende273.foody.feature.favorites.di.favoritesModule
import mende273.foody.feature.filtermeals.di.filterMealsModule
import mende273.foody.feature.mealdetails.di.mealDetailsModule
import mende273.foody.feature.random.di.randomModule
import mende273.foody.feature.search.di.searchModule
import org.koin.dsl.module

val appModule = module {
    includes(
        remoteDataSourceModule,
        localRepositoryModule,
        remoteRepositoryModule,
        databaseModule,
        dispatchersModule,
        favoritesModule,
        filterMealsModule,
        mealDetailsModule,
        mealsModule,
        searchModule,
        randomModule
    )
}
