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
    factory<AddFavoriteMealToDBUseCase> { AddFavoriteMealToDBUseCaseImpl(get()) }
    factory<DeleteFavoriteMealFromDBUseCase> { DeleteFavoriteMealFromDBUseCaseImpl(get()) }
    factory<GetFavoriteMealByIdFromDBUseCase> { GetFavoriteMealByIdFromDBUseCaseImpl(get()) }
    factory<GetAllFavoriteMealsFromDBUseCase> { GetAllFavoriteMealsFromDBUseCaseImpl(get()) }
}
