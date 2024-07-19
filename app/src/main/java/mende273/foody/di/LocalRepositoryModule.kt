package mende273.foody.di

import mende273.foody.data.repository.local.LocalRepositoryImpl
import mende273.foody.data.usecase.AddFavoriteMealToDBUseCaseImpl
import mende273.foody.data.usecase.DeleteFavoriteMealFromDBUseCaseImpl
import mende273.foody.data.usecase.GetAllFavoriteMealsFromDBUseCaseImpl
import mende273.foody.data.usecase.GetFavoriteMealByIdFromDBUseCaseImpl
import mende273.foody.domain.repository.LocalRepository
import mende273.foody.domain.usecase.AddFavoriteMealToDBUseCase
import mende273.foody.domain.usecase.DeleteFavoriteMealFromDBUseCase
import mende273.foody.domain.usecase.GetAllFavoriteMealsFromDBUseCase
import mende273.foody.domain.usecase.GetFavoriteMealByIdFromDBUseCase
import org.koin.dsl.module

val localRepositoryModule = module {
    single<LocalRepository> { LocalRepositoryImpl(get(), get()) }
    single<AddFavoriteMealToDBUseCase> { AddFavoriteMealToDBUseCaseImpl(get()) }
    single<DeleteFavoriteMealFromDBUseCase> { DeleteFavoriteMealFromDBUseCaseImpl(get()) }
    single<GetFavoriteMealByIdFromDBUseCase> { GetFavoriteMealByIdFromDBUseCaseImpl(get()) }
    single<GetAllFavoriteMealsFromDBUseCase> { GetAllFavoriteMealsFromDBUseCaseImpl(get()) }
}
