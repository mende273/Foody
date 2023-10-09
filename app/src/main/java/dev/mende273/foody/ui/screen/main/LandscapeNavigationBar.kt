package dev.mende273.foody.ui.screen.main

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import dev.mende273.foody.ui.theme.LightThemePrimary
import dev.mende273.foody.ui.theme.MediumDarkGreyColor
import dev.mende273.foody.ui.theme.NavigationBarSelectedColor

@Composable
fun LandscapeNavigationBar(
    navigationMenuItems: Array<NavigationMenuItem>,
    currentMenuItem: Int,
    onMenuItemClick: (Int, NavigationMenuItem) -> Unit
) {
    NavigationRail(
        modifier = Modifier,
        contentColor = MaterialTheme.colorScheme.secondary,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        navigationMenuItems.forEachIndexed { index, menuItem ->
            NavigationRailItem(
                icon = {
                    menuItem.icon?.let { painterResource(id = menuItem.icon) }?.let {
                        Icon(
                            painter = it,
                            contentDescription = stringResource(id = menuItem.resourceId)
                        )
                    }
                },
                label = { Text(text = stringResource(id = menuItem.resourceId)) },
                selected = currentMenuItem == index,
                onClick = { onMenuItemClick(index, menuItem) },
                alwaysShowLabel = true,
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = LightThemePrimary,
                    unselectedIconColor = MediumDarkGreyColor,
                    selectedTextColor = NavigationBarSelectedColor,
                    unselectedTextColor = MediumDarkGreyColor,
                    indicatorColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}
