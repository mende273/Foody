package mende273.foody.core.domain.usecase.remote

fun interface GetMealAreasUseCase {
    suspend operator fun invoke(): Result<List<String>>
}
