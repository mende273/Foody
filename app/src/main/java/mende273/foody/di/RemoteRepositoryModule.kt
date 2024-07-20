package mende273.foody.di

import mende273.foody.data.repository.remote.RemoteRepositoryImpl
import mende273.foody.data.usecase.GetAllFiltersUseCaseImpl
import mende273.foody.data.usecase.GetMealAreasUseCaseImpl
import mende273.foody.data.usecase.GetMealCategoriesUseCaseImpl
import mende273.foody.data.usecase.GetMealDetailsUseCaseImpl
import mende273.foody.data.usecase.GetMealsForAreaUseCaseImpl
import mende273.foody.data.usecase.GetMealsForCategoryUseCaseImpl
import mende273.foody.data.usecase.GetMealsForFirstLetterUseCaseImpl
import mende273.foody.data.usecase.GetMealsWithIngredientUseCaseImpl
import mende273.foody.data.usecase.GetRandomMealUseCaseImpl
import mende273.foody.data.usecase.SearchMealsByNameUseCaseImpl
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetAllFiltersUseCase
import mende273.foody.domain.usecase.GetMealAreasUseCase
import mende273.foody.domain.usecase.GetMealCategoriesUseCase
import mende273.foody.domain.usecase.GetMealDetailsUseCase
import mende273.foody.domain.usecase.GetMealsForAreaUseCase
import mende273.foody.domain.usecase.GetMealsForCategoryUseCase
import mende273.foody.domain.usecase.GetMealsForFirstLetterUseCase
import mende273.foody.domain.usecase.GetMealsWithIngredientUseCase
import mende273.foody.domain.usecase.GetRandomMealUseCase
import mende273.foody.domain.usecase.SearchMealsByNameUseCase
import org.koin.dsl.module

val remoteRepositoryModule = module {
    single<RemoteRepository> { RemoteRepositoryImpl(get(), get()) }
    single<GetAllFiltersUseCase> { GetAllFiltersUseCaseImpl(get(), get()) }
    single<GetRandomMealUseCase> { GetRandomMealUseCaseImpl(get()) }
    single<GetMealDetailsUseCase> { GetMealDetailsUseCaseImpl(get()) }
    single<GetMealsForCategoryUseCase> { GetMealsForCategoryUseCaseImpl(get()) }
    single<GetMealsForAreaUseCase> { GetMealsForAreaUseCaseImpl(get()) }
    single<GetMealsWithIngredientUseCase> { GetMealsWithIngredientUseCaseImpl(get()) }
    single<GetMealsForFirstLetterUseCase> { GetMealsForFirstLetterUseCaseImpl(get()) }
    single<GetMealCategoriesUseCase> { GetMealCategoriesUseCaseImpl(get()) }
    single<GetMealAreasUseCase> { GetMealAreasUseCaseImpl(get()) }
    single<SearchMealsByNameUseCase> { SearchMealsByNameUseCaseImpl(get()) }
}
