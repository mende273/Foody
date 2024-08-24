package mende273.foody.di

import mende273.foody.data.repository.local.LocalRepositoryImpl
import mende273.foody.data.usecase.local.AddFavoriteMealToDBUseCaseImpl
import mende273.foody.data.usecase.local.DeleteFavoriteMealFromDBUseCaseImpl
import mende273.foody.data.usecase.local.GetAllFavoriteMealsFromDBUseCaseImpl
import mende273.foody.data.usecase.local.GetFavoriteMealByIdFromDBUseCaseImpl
import mende273.foody.domain.repository.LocalRepository
import mende273.foody.domain.usecase.local.AddFavoriteMealToDBUseCase
import mende273.foody.domain.usecase.local.DeleteFavoriteMealFromDBUseCase
import mende273.foody.domain.usecase.local.GetAllFavoriteMealsFromDBUseCase
import mende273.foody.domain.usecase.local.GetFavoriteMealByIdFromDBUseCase
import org.koin.dsl.module

val localRepositoryModule = module {
    single<LocalRepository> { LocalRepositoryImpl(get(), get()) }
    single<AddFavoriteMealToDBUseCase> { AddFavoriteMealToDBUseCaseImpl(get()) }
    single<DeleteFavoriteMealFromDBUseCase> { DeleteFavoriteMealFromDBUseCaseImpl(get()) }
    single<GetFavoriteMealByIdFromDBUseCase> { GetFavoriteMealByIdFromDBUseCaseImpl(get()) }
    single<GetAllFavoriteMealsFromDBUseCase> { GetAllFavoriteMealsFromDBUseCaseImpl(get()) }
}
