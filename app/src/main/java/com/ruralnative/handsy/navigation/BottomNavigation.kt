package com.ruralnative.handsy.navigation

import androidx.compose.runtime.Composable

/**
 * Represents a bottom navigation item in the application.
 * This data class encapsulates the information needed to display a bottom navigation item,
 * including its title, route for navigation, and icons for both selected and unselected states.
 *
 * @property title The title of the navigation item, displayed as text.
 * @property route The route associated with this navigation item, used for navigation within the app.
 * @property selectedIcon A composable function that represents the icon for this navigation item when it is selected.
 * @property unselectedIcon A composable function that represents the icon for this navigation item when it is not selected.
 */
data class BottomNavigation(
    val title: String,
    val route: String,
    val selectedIcon: @Composable () -> Unit,
    val unselectedIcon: @Composable () -> Unit
)
