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
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import mende273.foody.main.NavigationBar
import mende273.foody.navigation.AppNavigation
import mende273.foody.navigation.Screen
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PortraitNavigationBarTest {

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
                windowSize = WindowSizeClass.calculateFromSize(DpSize(1080.dp, 2280.dp)),
                innerPadding = PaddingValues()
            )

            NavigationBar(
                currentScreen = Screen.Home,
                isPortrait = true,
                onNavigateToScreen = { navController.navigate(it) }
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
        val route = navController.currentDestination?.hasRoute(Screen.Home::class) == true
        Assert.assertEquals(route, true)
    }

    @Test
    fun appNavHost_verifyGoToSearchScreen() {
        val search = context.resources.getString(R.string.screen_title_search)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$search").performClick()
        val route = navController.currentDestination?.hasRoute(Screen.Search::class) == true
        Assert.assertEquals(route, true)
    }

    @Test
    fun appNavHost_verifyGoToFavouritesScreen() {
        val favourites = context.resources.getString(R.string.screen_title_favourites)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$favourites").performClick()
        val route = navController.currentDestination?.hasRoute(Screen.Favorites::class) == true
        Assert.assertEquals(route, true)
    }

    @Test
    fun appNavHost_verifyGoToRandomMealScreen() {
        val random = context.resources.getString(R.string.screen_title_random)
        composeTestRule.onNodeWithTag("test_tag_menu_item_$random").performClick()
        val route = navController.currentDestination?.hasRoute(Screen.Random::class) == true
        Assert.assertEquals(route, true)
    }
}
