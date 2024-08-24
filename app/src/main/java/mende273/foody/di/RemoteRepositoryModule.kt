package mende273.foody.di

import mende273.foody.data.repository.remote.RemoteRepositoryImpl
import mende273.foody.data.usecase.remote.GetAllFiltersUseCaseImpl
import mende273.foody.data.usecase.remote.GetMealAreasUseCaseImpl
import mende273.foody.data.usecase.remote.GetMealCategoriesUseCaseImpl
import mende273.foody.data.usecase.remote.GetMealDetailsUseCaseImpl
import mende273.foody.data.usecase.remote.GetMealsForAreaUseCaseImpl
import mende273.foody.data.usecase.remote.GetMealsForCategoryUseCaseImpl
import mende273.foody.data.usecase.remote.GetMealsForFirstLetterUseCaseImpl
import mende273.foody.data.usecase.remote.GetMealsWithIngredientUseCaseImpl
import mende273.foody.data.usecase.remote.GetRandomMealUseCaseImpl
import mende273.foody.data.usecase.remote.SearchMealsByNameUseCaseImpl
import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.remote.GetAllFiltersUseCase
import mende273.foody.domain.usecase.remote.GetMealAreasUseCase
import mende273.foody.domain.usecase.remote.GetMealCategoriesUseCase
import mende273.foody.domain.usecase.remote.GetMealDetailsUseCase
import mende273.foody.domain.usecase.remote.GetMealsForAreaUseCase
import mende273.foody.domain.usecase.remote.GetMealsForCategoryUseCase
import mende273.foody.domain.usecase.remote.GetMealsForFirstLetterUseCase
import mende273.foody.domain.usecase.remote.GetMealsWithIngredientUseCase
import mende273.foody.domain.usecase.remote.GetRandomMealUseCase
import mende273.foody.domain.usecase.remote.SearchMealsByNameUseCase
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
