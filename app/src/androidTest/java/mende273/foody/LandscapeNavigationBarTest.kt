package mende273.foody

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import mende273.foody.ui.main.NavigationBar
import mende273.foody.ui.navigation.AppNavigation
import mende273.foody.ui.navigation.Screen
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LandscapeNavigationBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController
    private val context: Context = ApplicationProvider.getApplicationContext()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            AppNavigation(
                navController = navController,
                windowSize = WindowSizeClass.calculateFromSize(DpSize(2280.dp, 1080.dp)),
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
        val meals = context.resources.getString(R.string.screen_title_meals)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$meals").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Screen.Meals.route)
    }

    @Test
    fun appNavHost_verifyGoToSearchScreen() {
        val search = context.resources.getString(R.string.screen_title_search)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$search").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Screen.Search.route)
    }

    @Test
    fun appNavHost_verifyGoToFavouritesScreen() {
        val favourites = context.resources.getString(R.string.screen_title_favourites)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$favourites").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Screen.Favourites.route)
    }

    @Test
    fun appNavHost_verifyGoToRandomMealScreen() {
        val random = context.resources.getString(R.string.screen_title_random)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$random").performClick()
        val route = navController.currentDestination?.route
        Assert.assertEquals(route, Screen.RandomMeal.route)
    }
}
