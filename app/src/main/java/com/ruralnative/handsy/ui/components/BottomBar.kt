package com.ruralnative.handsy.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.ruralnative.handsy.R
import com.ruralnative.handsy.navigation.data.BottomNavigation
import com.ruralnative.handsy.navigation.data.Screen

/**
 * A composable function that displays a bottom navigation bar.
 * This bottom navigation bar includes navigation items for Lesson, Home, and Camera screens.
 * Each navigation item displays an icon and a title, and the icon changes based on whether the item is selected.
 *
 * @param currentDestination The current navigation destination. Used to determine which navigation item is currently selected.
 * @param onNavigateToLessonListScreen A lambda function to navigate to the Lesson List screen.
 * @param onNavigateToHomeScreen A lambda function to navigate to the Home screen.
 * @param onNavigateToCameraSetupScreen A lambda function to navigate to the Camera Setup screen.
 */
@Composable
fun BottomBar(
    currentDestination: NavDestination?,
    onNavigateToLessonListScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToCameraSetupScreen: () -> Unit
) {
    NavigationBar {
        val navigationItems = listOf(
            BottomNavigation(
                title = "Lesson",
                route = Screen.LessonListScreen.route,
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
                route = Screen.HomeScreen.route,
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
                route = Screen.CameraSetupScreen.route,
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
                onClick = {
                    when (item.route) {
                        Screen.HomeScreen.route -> onNavigateToHomeScreen()
                        Screen.LessonListScreen.route -> onNavigateToLessonListScreen()
                        Screen.CameraSetupScreen.route -> onNavigateToCameraSetupScreen()
                    }
                },
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

/**
 * A composable function that displays an icon for a selected navigation item.
 * This function is used to display the icon for a navigation item when it is selected.
 *
 * @param modifier The modifier to apply to the icon.
 * @param resourceID The resource ID of the drawable to display as the icon.
 * @param contentDescription The content description for the icon, used for accessibility purposes.
 */
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

/**
 * A composable function that displays an icon for an unselected navigation item.
 * This function is used to display the icon for a navigation item when it is not selected.
 *
 * @param modifier The modifier to apply to the icon.
 * @param resourceID The resource ID of the drawable to display as the icon.
 * @param contentDescription The content description for the icon, used for accessibility purposes.
 */
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