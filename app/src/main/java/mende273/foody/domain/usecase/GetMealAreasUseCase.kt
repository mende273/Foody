package mende273.foody.domain.usecase

fun interface GetMealAreasUseCase {
    suspend operator fun invoke(): Result<List<String>>
}
