package mende273.foody.data.usecase

import mende273.foody.domain.repository.RemoteRepository
import mende273.foody.domain.usecase.GetMealCategoriesUseCase

class GetMealCategoriesUseCaseImpl(
    private val remoteRepository: RemoteRepository
) : GetMealCategoriesUseCase {
    override suspend fun invoke(): Result<List<String>> =
        remoteRepository.getMealCategories()
}
