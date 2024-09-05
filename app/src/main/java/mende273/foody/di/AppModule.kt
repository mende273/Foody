package mende273.foody.di

import mende273.foody.core.data.di.databaseModule
import mende273.foody.core.data.di.dispatchersModule
import mende273.foody.core.data.di.localRepositoryModule
import mende273.foody.core.data.di.remoteDataSourceModule
import mende273.foody.core.data.di.remoteRepositoryModule
import mende273.foody.feature.favorites.di.favoritesModule
import mende273.foody.feature.filtermeals.di.filterMealsModule
import mende273.foody.feature.mealdetails.di.mealDetailsModule
import org.koin.dsl.module

val appModule = module {
    includes(
        remoteDataSourceModule,
        localRepositoryModule,
        remoteRepositoryModule,
        viewModelModule,
        databaseModule,
        dispatchersModule,
        favoritesModule,
        filterMealsModule,
        mealDetailsModule
    )
}
