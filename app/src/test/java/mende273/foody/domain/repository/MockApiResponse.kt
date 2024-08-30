package mende273.foody.domain.repository

import io.ktor.client.request.HttpRequestData
import mende273.foody.core.domain.model.IngredientWithMeasure
import mende273.foody.core.domain.model.MealDetails

fun getMockResponseForRequest(request: HttpRequestData): String {
    val path = request.url.encodedPath
    val parameters = request.url.parameters

    return if (path.contains("random.php")
    ) {
        MOCK_RESPONSE_FOR_MEAL_DETAILS
    } else if (path.contains("filter.php")) {
        if (parameters.contains("c")) {
            MOCK_RESPONSE_FOR_CATEGORY_MEALS
        } else if (parameters.contains("a")) {
            MOCK_RESPONSE_FOR_AREA_MEALS
        } else if (parameters.contains("i")) {
            MOCK_RESPONSE_FOR_INGREDIENT_MEALS
        } else {
            ""
        }
    } else if (path.contains("search.php")) {
        if (parameters.contains("f")) {
            MOCK_RESPONSE_FOR_MEALS_WITH_FIRST_LETTER
        } else if (parameters.contains("s")) {
            MOCK_RESPONSE_FOR_SEARCH_MEAL
        } else {
            ""
        }
    } else if (path.contains("lookup.php")) {
        MOCK_RESPONSE_FOR_MEAL_DETAILS
    } else if (path.contains("list.php")) {
        if (parameters.contains("c")) {
            MOCK_RESPONSE_FOR_CATEGORIES
        } else if (parameters.contains("a")) {
            MOCK_RESPONSE_FOR_AREAS
        } else {
            ""
        }
    } else {
        ""
    }
}

fun provideMockAreas() = listOf(
    "American", "British", "Canadian", "Chinese",
    "Croatian", "Dutch", "Egyptian", "Filipino",
    "French", "Greek", "Indian", "Irish", "Italian",
    "Jamaican", "Japanese", "Kenyan", "Malaysian",
    "Mexican", "Moroccan", "Polish", "Portuguese",
    "Russian", "Spanish", "Thai", "Tunisian",
    "Turkish", "Unknown", "Vietnamese"
)

fun provideMockCategories(): List<String> = listOf(
    "Beef", "Breakfast", "Chicken", "Dessert", "Goat",
    "Lamb", "Miscellaneous", "Pasta", "Pork", "Seafood",
    "Side", "Starter", "Vegan", "Vegetarian"
)

fun provideMockMealDetailsObject(): MealDetails = MealDetails(
    id = 52999,
    name = "Crispy Sausages and Greens",
    category = "Pork",
    area = "Irish",
    instructions = "Preheat the oven to 350°. Remove the stems from one bunch" +
        " of Tuscan kale and tear the leaves into 1",
    thumb = "https://www.themealdb.com/images/media/" +
        "meals/st1ifa1583267248.jpg",
    tags = "",
    youtube = "",
    source = "https://www.bonappetit.com/columns/cooking-without-recipes/" +
        "article/kale-cabbage-sausage-weeknight-dinner",
    ingredientsWithMeasures = listOf(
        IngredientWithMeasure("Kale", "1  bunch"),
        IngredientWithMeasure("Italian Fennel Sausages", "8"),
        IngredientWithMeasure("Cabbage", "1 Head chopped"),
        IngredientWithMeasure("Garlic Clove", "8"),
        IngredientWithMeasure("Onion", "Sliced"),
        IngredientWithMeasure("Shiitake Mushrooms", "Sliced"),
        IngredientWithMeasure("Chicken Stock", "1 cup"),
        IngredientWithMeasure("Salt", ""),
        IngredientWithMeasure("Pepper", "")
    )
)

private const val MOCK_RESPONSE_FOR_MEAL_DETAILS = """{
                        "meals": [
    {
      "idMeal": "52999",
      "strMeal": "Crispy Sausages and Greens",
      "strDrinkAlternate": null,
      "strCategory": "Pork",
      "strArea": "Irish",
      "strInstructions": "Preheat the oven to 350°. Remove the stems from one bunch of Tuscan kale and tear the leaves into 1",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/st1ifa1583267248.jpg",
      "strTags": null,
      "strYoutube": "",
      "strIngredient1": "Kale",
      "strIngredient2": "Italian Fennel Sausages",
      "strIngredient3": "Cabbage",
      "strIngredient4": "Garlic Clove",
      "strIngredient5": "Onion",
      "strIngredient6": "Shiitake Mushrooms",
      "strIngredient7": "Chicken Stock",
      "strIngredient8": "Salt",
      "strIngredient9": "Pepper",
      "strIngredient10": "",
      "strIngredient11": "",
      "strIngredient12": "",
      "strIngredient13": "",
      "strIngredient14": "",
      "strIngredient15": "",
      "strIngredient16": "",
      "strIngredient17": "",
      "strIngredient18": "",
      "strIngredient19": "",
      "strIngredient20": "",
      "strMeasure1": "1  bunch",
      "strMeasure2": "8",
      "strMeasure3": "1 Head chopped",
      "strMeasure4": "8",
      "strMeasure5": "Sliced",
      "strMeasure6": "Sliced",
      "strMeasure7": "1 cup ",
      "strMeasure8": " ",
      "strMeasure9": " ",
      "strMeasure10": " ",
      "strMeasure11": " ",
      "strMeasure12": " ",
      "strMeasure13": " ",
      "strMeasure14": " ",
      "strMeasure15": " ",
      "strMeasure16": " ",
      "strMeasure17": " ",
      "strMeasure18": " ",
      "strMeasure19": " ",
      "strMeasure20": " ",
      "strSource": "https://www.bonappetit.com/columns/cooking-without-recipes/article/kale-cabbage-sausage-weeknight-dinner",
      "strImageSource": null,
      "strCreativeCommonsConfirmed": null,
      "dateModified": 0
    }
  ]
}"""

private const val MOCK_RESPONSE_FOR_CATEGORY_MEALS = """
{
  "meals": [
    {
      "strMeal": "Bread omelette",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/hqaejl1695738653.jpg",
      "idMeal": "53076"
    },
    {
      "strMeal": "Breakfast Potatoes",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/1550441882.jpg",
      "idMeal": "52965"
    },
    {
      "strMeal": "English Breakfast",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/utxryw1511721587.jpg",
      "idMeal": "52895"
    },
    {
      "strMeal": "Fruit and Cream Cheese Breakfast Pastries",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/1543774956.jpg",
      "idMeal": "52957"
    },
    {
      "strMeal": "Full English Breakfast",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/sqrtwu1511721265.jpg",
      "idMeal": "52896"
    },
    {
      "strMeal": "Home-made Mandazi",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/thazgm1555350962.jpg",
      "idMeal": "52967"
    },
    {
      "strMeal": "Salmon Eggs Eggs Benedict",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/1550440197.jpg",
      "idMeal": "52962"
    },
    {
      "strMeal": "Smoked Haddock Kedgeree",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/1550441275.jpg",
      "idMeal": "52964"
    }
  ]
}
"""

private const val MOCK_RESPONSE_FOR_AREA_MEALS = """
    {
  "meals": [
    {
      "strMeal": "Burek",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/tkxquw1628771028.jpg",
      "idMeal": "53060"
    },
    {
      "strMeal": "Cevapi Sausages",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/vc08jn1628769553.jpg",
      "idMeal": "53055"
    },
    {
      "strMeal": "Croatian Bean Stew",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/tnwy8m1628770384.jpg",
      "idMeal": "53058"
    },
    {
      "strMeal": "Croatian lamb peka",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/pn59o51628769837.jpg",
      "idMeal": "53056"
    },
    {
      "strMeal": "Fresh sardines",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/nv5lf31628771380.jpg",
      "idMeal": "53061"
    },
    {
      "strMeal": "Mushroom soup with buckwheat",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/1ngcbf1628770793.jpg",
      "idMeal": "53059"
    },
    {
      "strMeal": "Traditional Croatian Goulash",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/n1hcou1628770088.jpg",
      "idMeal": "53057"
    },
    {
      "strMeal": "Walnut Roll Gužvara",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/u9l7k81628771647.jpg",
      "idMeal": "53062"
    }
  ]
}
"""

private const val MOCK_RESPONSE_FOR_INGREDIENT_MEALS = """
    {
  "meals": [
    {
      "strMeal": "Chick-Fil-A Sandwich",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/sbx7n71587673021.jpg",
      "idMeal": "53016"
    },
    {
      "strMeal": "Chicken Couscous",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/qxytrx1511304021.jpg",
      "idMeal": "52850"
    },
    {
      "strMeal": "Chicken Fajita Mac and Cheese",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/qrqywr1503066605.jpg",
      "idMeal": "52818"
    },
    {
      "strMeal": "Chicken Ham and Leek Pie",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/xrrtss1511555269.jpg",
      "idMeal": "52875"
    },
    {
      "strMeal": "Chicken Quinoa Greek Salad",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/k29viq1585565980.jpg",
      "idMeal": "53011"
    },
    {
      "strMeal": "General Tso's Chicken",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/1529444113.jpg",
      "idMeal": "52951"
    },
    {
      "strMeal": "Honey Balsamic Chicken with Crispy Broccoli & Potatoes",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/kvbotn1581012881.jpg",
      "idMeal": "52993"
    },
    {
      "strMeal": "Katsu Chicken curry",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/vwrpps1503068729.jpg",
      "idMeal": "52820"
    },
    {
      "strMeal": "Rappie Pie",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/ruwpww1511817242.jpg",
      "idMeal": "52933"
    }
  ]
}
"""

private const val MOCK_RESPONSE_FOR_MEALS_WITH_FIRST_LETTER = """
    {
  "meals": [
    {
      "idMeal": "52871",
      "strMeal": "Yaki Udon",
      "strDrinkAlternate": null,
      "strCategory": "Vegetarian",
      "strArea": "Japanese",
      "strInstructions": "Boil some water in a large saucepan. Add 250ml cold water and the udon noodles. (As they are so thick, adding cold water helps them to cook a little bit slower so the middle cooks through). If using frozen or fresh noodles, cook for 2 mins or until al dente; dried will take longer, about 5-6 mins. Drain and leave in the colander.\r\nHeat 1 tbsp of the oil, add the onion and cabbage and sauté for 5 mins until softened. Add the mushrooms and some spring onions, and sauté for 1 more min. Pour in the remaining sesame oil and the noodles. If using cold noodles, let them heat through before adding the ingredients for the sauce – otherwise tip in straight away and keep stir-frying until sticky and piping hot. Sprinkle with the remaining spring onions.",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/wrustq1511475474.jpg",
      "strTags": "LowCalorie",
      "strYoutube": "https://www.youtube.com/watch?v=5Iy0MCowSvA",
      "strIngredient1": "Udon Noodles",
      "strIngredient2": "Sesame Seed Oil",
      "strIngredient3": "Onion",
      "strIngredient4": "Cabbage",
      "strIngredient5": "Shiitake Mushrooms",
      "strIngredient6": "Spring Onions",
      "strIngredient7": "Mirin",
      "strIngredient8": "Soy Sauce",
      "strIngredient9": "Caster Sugar",
      "strIngredient10": "Worcestershire Sauce",
      "strIngredient11": "",
      "strIngredient12": "",
      "strIngredient13": "",
      "strIngredient14": "",
      "strIngredient15": "",
      "strIngredient16": "",
      "strIngredient17": "",
      "strIngredient18": "",
      "strIngredient19": "",
      "strIngredient20": "",
      "strMeasure1": "250g",
      "strMeasure2": "2 tbs",
      "strMeasure3": "1 sliced",
      "strMeasure4": "0.25",
      "strMeasure5": "10",
      "strMeasure6": "4",
      "strMeasure7": "4 tbsp",
      "strMeasure8": "2 tbs",
      "strMeasure9": "1 tblsp ",
      "strMeasure10": "1 tblsp ",
      "strMeasure11": "",
      "strMeasure12": "",
      "strMeasure13": "",
      "strMeasure14": "",
      "strMeasure15": "",
      "strMeasure16": "",
      "strMeasure17": "",
      "strMeasure18": "",
      "strMeasure19": "",
      "strMeasure20": "",
      "strSource": "https://www.bbcgoodfood.com/recipes/yaki-udon",
      "strImageSource": null,
      "strCreativeCommonsConfirmed": null,
      "dateModified": null
    }
  ]
}
"""

private const val MOCK_RESPONSE_FOR_CATEGORIES = """
    {
  "meals": [
    {
      "strCategory": "Beef"
    },
    {
      "strCategory": "Breakfast"
    },
    {
      "strCategory": "Chicken"
    },
    {
      "strCategory": "Dessert"
    },
    {
      "strCategory": "Goat"
    },
    {
      "strCategory": "Lamb"
    },
    {
      "strCategory": "Miscellaneous"
    },
    {
      "strCategory": "Pasta"
    },
    {
      "strCategory": "Pork"
    },
    {
      "strCategory": "Seafood"
    },
    {
      "strCategory": "Side"
    },
    {
      "strCategory": "Starter"
    },
    {
      "strCategory": "Vegan"
    },
    {
      "strCategory": "Vegetarian"
    }
  ]
}
"""

private const val MOCK_RESPONSE_FOR_AREAS = """
    {
  "meals": [
    {
      "strArea": "American"
    },
    {
      "strArea": "British"
    },
    {
      "strArea": "Canadian"
    },
    {
      "strArea": "Chinese"
    },
    {
      "strArea": "Croatian"
    },
    {
      "strArea": "Dutch"
    },
    {
      "strArea": "Egyptian"
    },
    {
      "strArea": "Filipino"
    },
    {
      "strArea": "French"
    },
    {
      "strArea": "Greek"
    },
    {
      "strArea": "Indian"
    },
    {
      "strArea": "Irish"
    },
    {
      "strArea": "Italian"
    },
    {
      "strArea": "Jamaican"
    },
    {
      "strArea": "Japanese"
    },
    {
      "strArea": "Kenyan"
    },
    {
      "strArea": "Malaysian"
    },
    {
      "strArea": "Mexican"
    },
    {
      "strArea": "Moroccan"
    },
    {
      "strArea": "Polish"
    },
    {
      "strArea": "Portuguese"
    },
    {
      "strArea": "Russian"
    },
    {
      "strArea": "Spanish"
    },
    {
      "strArea": "Thai"
    },
    {
      "strArea": "Tunisian"
    },
    {
      "strArea": "Turkish"
    },
    {
      "strArea": "Unknown"
    },
    {
      "strArea": "Vietnamese"
    }
  ]
}
"""

private const val MOCK_RESPONSE_FOR_SEARCH_MEAL = """
    {
  "meals": [
    {
      "idMeal": "52771",
      "strMeal": "Spicy Arrabiata Penne",
      "strDrinkAlternate": null,
      "strCategory": "Vegetarian",
      "strArea": "Italian",
      "strInstructions": "Bring a large pot of water to a boil. Add kosher salt to the boiling water, then add the pasta. Cook according to the package instructions, about 9 minutes.\r\nIn a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste. Bring to a boil and cook for 5 minutes. Remove from the heat and add the chopped basil.\r\nDrain the pasta and add it to the sauce. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm.",
      "strMealThumb": "https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
      "strTags": "Pasta,Curry",
      "strYoutube": "https://www.youtube.com/watch?v=1IszT_guI08",
      "strIngredient1": "penne rigate",
      "strIngredient2": "olive oil",
      "strIngredient3": "garlic",
      "strIngredient4": "chopped tomatoes",
      "strIngredient5": "red chile flakes",
      "strIngredient6": "italian seasoning",
      "strIngredient7": "basil",
      "strIngredient8": "Parmigiano-Reggiano",
      "strIngredient9": "",
      "strIngredient10": "",
      "strIngredient11": "",
      "strIngredient12": "",
      "strIngredient13": "",
      "strIngredient14": "",
      "strIngredient15": "",
      "strIngredient16": null,
      "strIngredient17": null,
      "strIngredient18": null,
      "strIngredient19": null,
      "strIngredient20": null,
      "strMeasure1": "1 pound",
      "strMeasure2": "1/4 cup",
      "strMeasure3": "3 cloves",
      "strMeasure4": "1 tin ",
      "strMeasure5": "1/2 teaspoon",
      "strMeasure6": "1/2 teaspoon",
      "strMeasure7": "6 leaves",
      "strMeasure8": "spinkling",
      "strMeasure9": "",
      "strMeasure10": "",
      "strMeasure11": "",
      "strMeasure12": "",
      "strMeasure13": "",
      "strMeasure14": "",
      "strMeasure15": "",
      "strMeasure16": null,
      "strMeasure17": null,
      "strMeasure18": null,
      "strMeasure19": null,
      "strMeasure20": null,
      "strSource": null,
      "strImageSource": null,
      "strCreativeCommonsConfirmed": null,
      "dateModified": null
    }
  ]
}
"""
