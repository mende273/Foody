package mende273.foody.domain.usecase.remote

fun interface GetMealCategoriesUseCase {
    suspend operator fun invoke(): Result<List<String>>
}
