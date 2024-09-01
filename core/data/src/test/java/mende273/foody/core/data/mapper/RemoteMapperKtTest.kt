package mende273.foody.core.data.mapper

import junit.framework.TestCase.assertEquals
import mende273.foody.core.data.dto.MealCategoriesDto
import mende273.foody.core.data.dto.MealCategoryDto
import mende273.foody.core.data.dto.MealDetailsDto
import mende273.foody.core.data.dto.MealDto
import mende273.foody.core.data.dto.MealsDto
import mende273.foody.core.data.dto.MealsWithDetailsDto
import mende273.foody.core.domain.model.IngredientWithMeasure
import mende273.foody.core.domain.model.Meal
import mende273.foody.core.domain.model.MealDetails
import org.junit.Test

class RemoteMapperKtTest {

    private val mealDetailsDto = MealDetailsDto(
        id = 12345,
        name = "Spaghetti Carbonara",
        category = "Italian",
        area = "Italy",
        instructions = "A delicious pasta dish with bacon, eggs, and cheese.",
        thumb = "https://www.example.com/spaghetti.jpg",
        tags = "spaghetti, italian",
        youtube = "https://www.youtube.com/watch?v=12345",
        source = "https://www.example.com/recipe/spaghetti-carbonara",
        ingredient1 = "bacon",
        measure1 = "1 cup",
        ingredient2 = "eggs",
        measure2 = "3",
        ingredient3 = "cheese",
        measure3 = "1 cup",
        ingredient4 = "pasta",
        measure4 = "2 cups"
    )

    @Test
    fun `should convert a MealDetailsDto to a MealDetails object`() {
        val actualMealDetails = mealDetailsDto.toDomainModel()

        val expectedMealDetails = MealDetails(
            id = 12345L,
            name = "Spaghetti Carbonara",
            category = "Italian",
            area = "Italy",
            instructions = "A delicious pasta dish with bacon, eggs, and cheese.",
            thumb = "https://www.example.com/spaghetti.jpg",
            tags = "spaghetti, italian",
            youtube = "https://www.youtube.com/watch?v=12345",
            source = "https://www.example.com/recipe/spaghetti-carbonara",
            ingredientsWithMeasures = listOf(
                IngredientWithMeasure("bacon", "1 cup"),
                IngredientWithMeasure("eggs", "3"),
                IngredientWithMeasure("cheese", "1 cup"),
                IngredientWithMeasure("pasta", "2 cups")
            )
        )

        assertEquals(expectedMealDetails, actualMealDetails)
    }

    @Test
    fun `MealsWithDetailsDto with non-null mealsWithDetails to a list `() {
        val mealsWithDetailsDto = MealsWithDetailsDto(
            mealsWithDetails = listOf(mealDetailsDto)
        )

        val actualMealDetailsList = mealsWithDetailsDto.toDomainModel()

        val expectedMealDetailsList = listOf(
            MealDetails(
                id = 12345L,
                name = "Spaghetti Carbonara",
                category = "Italian",
                area = "Italy",
                instructions = "A delicious pasta dish with bacon, eggs, and cheese.",
                thumb = "https://www.example.com/spaghetti.jpg",
                tags = "spaghetti, italian",
                youtube = "https://www.youtube.com/watch?v=12345",
                source = "https://www.example.com/recipe/spaghetti-carbonara",
                ingredientsWithMeasures = listOf(
                    IngredientWithMeasure("bacon", "1 cup"),
                    IngredientWithMeasure("eggs", "3"),
                    IngredientWithMeasure("cheese", "1 cup"),
                    IngredientWithMeasure("pasta", "2 cups")
                )
            )
        )

        assertEquals(expectedMealDetailsList, actualMealDetailsList)
    }

    @Test
    fun `should return an empty list if mealsWithDetails is null`() {
        val mealsWithDetailsDto = MealsWithDetailsDto(mealsWithDetails = null)

        val actualMealDetailsList = mealsWithDetailsDto.toDomainModel()

        assertEquals(emptyList<MealDetails>(), actualMealDetailsList)
    }

    @Test
    fun `should convert a MealsDto with non-null meals to a list of Meal objects`() {
        val mealsDto = MealsDto(
            meals = listOf(
                MealDto(
                    id = 12345,
                    name = "Spaghetti Carbonara",
                    thumb = "https://www.example.com/spaghetti.jpg"
                ),
                MealDto(
                    id = 52772,
                    name = "Teriyaki Chicken Casserole",
                    thumb = "https://www.example.com/teriyaki-chicken.jpg"
                )
            )
        )

        val actualMealList = mealsDto.toDomainModel()

        val expectedMealList = listOf(
            Meal(
                id = 12345L,
                name = "Spaghetti Carbonara",
                thumb = "https://www.example.com/spaghetti.jpg"
            ),
            Meal(
                id = 52772L,
                name = "Teriyaki Chicken Casserole",
                thumb = "https://www.example.com/teriyaki-chicken.jpg"
            )
        )

        assertEquals(expectedMealList, actualMealList)
    }

    @Test
    fun `should return an empty list if meals is null`() {
        val mealsDto = MealsDto(meals = null)

        val actualMealList = mealsDto.toDomainModel()

        assertEquals(emptyList<Meal>(), actualMealList)
    }

    @Test
    fun `should convert a MealDto to a Meal object`() {
        val mealDto = MealDto(
            id = 12345L,
            name = "Spaghetti Carbonara",
            thumb = "https://www.example.com/spaghetti.jpg"
        )

        val actualMeal = mealDto.toDomainModel()

        assertEquals(12345L, actualMeal.id)
        assertEquals("Spaghetti Carbonara", actualMeal.name)
        assertEquals("https://www.example.com/spaghetti.jpg", actualMeal.thumb)
    }

    @Test
    fun `should use default values if thumb or name is null`() {
        val mealDto = MealDto(
            id = 52772L,
            name = null,
            thumb = null
        )

        val actualMeal = mealDto.toDomainModel()

        assertEquals(52772L, actualMeal.id)
        assertEquals("", actualMeal.name)
        assertEquals("", actualMeal.thumb)
    }

    @Test
    fun `MealCategoriesDto with non-null categories to a list of category names`() {
        val mealCategoriesDto = MealCategoriesDto(
            categories = listOf(
                MealCategoryDto(category = "Italian"),
                MealCategoryDto(category = "Chinese"),
                MealCategoryDto(category = "Mexican")
            )
        )

        val actualCategoryNames = mealCategoriesDto.toDomainModel()

        val expectedCategoryNames = listOf("Italian", "Chinese", "Mexican")

        assertEquals(expectedCategoryNames, actualCategoryNames)
    }

    @Test
    fun `should return an empty list if categories is null or contains only null values`() {
        val mealCategoriesDto = MealCategoriesDto(categories = null)

        val actualCategoryNames = mealCategoriesDto.toDomainModel()

        assertEquals(emptyList<String>(), actualCategoryNames)
    }

    @Test
    fun `should only include categories with non-empty names`() {
        val mealCategoriesDto = MealCategoriesDto(
            categories = listOf(
                MealCategoryDto(category = ""),
                MealCategoryDto(category = null),
                MealCategoryDto(category = " "),
                MealCategoryDto(category = "Italian")
            )
        )

        val actualCategoryNames = mealCategoriesDto.toDomainModel()

        val expectedCategoryNames = listOf("Italian")

        assertEquals(expectedCategoryNames, actualCategoryNames)
    }
}
