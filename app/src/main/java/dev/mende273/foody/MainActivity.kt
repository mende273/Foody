package dev.mende273.foody

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dev.mende273.foody.navigation.AppNavigation
import dev.mende273.foody.ui.component.BottomNavigationComponent
import dev.mende273.foody.ui.theme.FoodyTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberNavController()

            FoodyTheme {
                Scaffold(
                    content = { innerPadding ->
                        AppNavigation(
                            modifier = Modifier.fillMaxSize(),
                            navHostController = navController,
                            innerPadding = innerPadding
                        )
                    },
                    bottomBar = { BottomNavigationComponent(navController) }
                )
            }
        }
    }
}
