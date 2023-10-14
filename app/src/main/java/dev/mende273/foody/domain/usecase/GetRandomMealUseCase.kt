package dev.mende273.foody.domain.usecase

import dev.mende273.foody.data.repository.RemoteRepositoryImpl
import dev.mende273.foody.domain.model.Meal
import dev.mende273.foody.mapper.toDomain
import dev.mende273.foody.util.exception.RetrieveNetworkDataException

class GetRandomMealUseCase(private val remoteRepository: RemoteRepositoryImpl) {

    suspend operator fun invoke(): Meal =
        remoteRepository.getRandomMeal()
            .getOrThrow()
            .takeIf { it.meals != null }
            ?.toDomain()
            ?.meals?.get(0)
            ?: throw RetrieveNetworkDataException()
}
