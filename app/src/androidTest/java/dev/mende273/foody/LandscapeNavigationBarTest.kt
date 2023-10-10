package dev.mende273.foody

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import dev.mende273.foody.navigation.AppNavigation
import dev.mende273.foody.navigation.Screen
import dev.mende273.foody.ui.screen.main.NavigationBar
import dev.mende273.foody.ui.screen.main.NavigationMenuItem
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LandscapeNavigationBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController
    private val context: Context = ApplicationProvider.getApplicationContext()

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            AppNavigation(
                navController = navController,
                innerPadding = PaddingValues()
            )

            NavigationBar(
                navController = navController,
                isPortrait = false
            )
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule
            .onNodeWithTag("test_tag_meals_screen")
            .assertIsDisplayed()
    }

    @Test
    fun appNavHost_verifyGoToMealsScreen() {
        val meals = context.resources.getString(NavigationMenuItem.MEALS.title)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$meals").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Screen.Meals.route)
    }

    @Test
    fun appNavHost_verifyGoToSearchScreen() {
        val search = context.resources.getString(NavigationMenuItem.SEARCH.title)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$search").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Screen.Search.route)
    }

    @Test
    fun appNavHost_verifyGoToFavouritesScreen() {
        val favourites = context.resources.getString(NavigationMenuItem.FAVORITES.title)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$favourites").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Screen.Favourites.route)
    }

    @Test
    fun appNavHost_verifyGoToRandomMealScreen() {
        val random = context.resources.getString(NavigationMenuItem.RANDOM_MEAL.title)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$random").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Screen.RandomMeal.route)
    }
}
