package com.ruralnative.handsy.navigation

/**
 * Type-safe object containing String-typed routes which serves as unique identifiers for each Composable screens in the Navigation Graph
 */
sealed class Screen(val route: String) {
    data object Entry: Screen(route = "entry_screen")
    data object UserIntro: Screen(route = "user_intro_screen")
    data object MainScreen: Screen(route = "main_screen")
    data object CameraScreen: Screen(route = "camera_screen")
    data object StatsScreen: Screen(route = "stats_screen")
}