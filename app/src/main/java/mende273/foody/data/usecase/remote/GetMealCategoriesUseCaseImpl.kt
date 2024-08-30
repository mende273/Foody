package mende273.foody.data.usecase.remote

import mende273.foody.core.domain.repository.RemoteRepository
import mende273.foody.core.domain.usecase.remote.GetMealCategoriesUseCase

class GetMealCategoriesUseCaseImpl(
    private val remoteRepository: RemoteRepository
) : GetMealCategoriesUseCase {
    override suspend fun invoke(): Result<List<String>> =
        remoteRepository.getMealCategories()
}
