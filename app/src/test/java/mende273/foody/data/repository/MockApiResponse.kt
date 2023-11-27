package mende273.foody.data.repository

import io.ktor.client.request.HttpRequestData
import mende273.foody.domain.model.IngredientWithMeasure
import mende273.foody.domain.model.MealDetails

fun getMockResponseForRequest(request: HttpRequestData): String {
    return if (request.url.encodedPath.contains("random.php")
    ) {
        MOCK_RESPONSE_FOR_RANDOM_MEAL
    } else if (request.url.encodedPath.contains("filter.php")) {
        if (request.url.parameters.contains("c")) {
            MOCK_RESPONSE_FOR_CATEGORY_MEALS
        } else if (request.url.parameters.contains("a")) {
            MOCK_RESPONSE_FOR_AREA_MEALS
        } else if (request.url.parameters.contains("i")) {
            MOCK_RESPONSE_FOR_INGREDIENT_MEALS
        } else {
            ""
        }
    } else {
        ""
    }
}

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

private const val MOCK_RESPONSE_FOR_RANDOM_MEAL = """{
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
