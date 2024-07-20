package mende273.foody.domain.usecase

fun interface GetMealCategoriesUseCase {
    suspend operator fun invoke(): Result<List<String>>
}
