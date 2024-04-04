package com.ruralnative.handsy.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ruralnative.handsy.R
import com.ruralnative.handsy.navigation.BottomNavigation
import com.ruralnative.handsy.navigation.Screen

@Composable
fun BottomBar(
    navigationController: NavHostController
) {
    val navBackStackEntry by navigationController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        val navigationItems = listOf(
            BottomNavigation(
                title = "Lesson",
                route = Screen.MainScreen.route,
                selectedIcon = {
                    SelectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_lesson,
                        contentDescription = "Lesson Navigation Button"
                    )
                },
                unselectedIcon = {
                    UnselectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_lesson,
                        contentDescription = "Lesson Navigation Button"
                    )
                }
            ),
            BottomNavigation(
                title = "Home",
                route = Screen.MainScreen.route,
                selectedIcon = {
                    SelectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_home,
                        contentDescription = "Home Navigation Button"
                    )
                },
                unselectedIcon = {
                    UnselectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_home,
                        contentDescription = "Home Navigation Button"
                    )
                }
            ),
            BottomNavigation(
                title = "Camera",
                route = Screen.CameraScreen.route,
                selectedIcon = {
                    SelectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_camera,
                        contentDescription = "Camera Navigation Button"
                    )
                },
                unselectedIcon = {
                    UnselectedIcon(
                        modifier = Modifier,
                        resourceID = R.drawable.icon_camera,
                        contentDescription = "Camera Navigation Button"
                    )
                }
            )
        )

        val selectionMap = remember(currentDestination) {
            navigationItems.associateWith { item ->
                currentDestination?.hierarchy?.any { it.route == item.route } == true
            }
        }

        navigationItems.forEach { item ->
            val selected = selectionMap.getOrDefault(item, false)
            NavigationBarItem(
                selected = selected,
                onClick = { navigationController.navigate(item.route) },
                icon = {
                    if (selected) {
                        item.selectedIcon()
                    } else  {
                        item.unselectedIcon()
                    }
                },
                label = {
                    Text(
                        item.title,
                        fontWeight =
                            if (selected) FontWeight.Bold
                            else FontWeight.Normal
                    )
                }
            )
        }
    }
}

@Composable
private fun SelectedIcon(
    modifier: Modifier,
    @DrawableRes resourceID: Int,
    contentDescription: String,
) {
    Icon(
        painter = painterResource(id = resourceID),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = Color.Unspecified
    )
}

@Composable
private fun UnselectedIcon(
    modifier: Modifier,
    @DrawableRes resourceID: Int,
    contentDescription: String,
) {
    Icon(
        painter = painterResource(id = resourceID),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = Color.Unspecified
    )
}