package com.ruralnative.handsy.navigation

/**
 * Type-safe object containing String-typed routes which serves as unique identifiers for each Composable screens in the Navigation Graph
 */
sealed class Screen(val route: String) {
    data object Entry: Screen(route = "entry_screen")
    data object UserIntro: Screen(route = "user_intro_screen")
    data object DevsIntro: Screen(route = "developers_intro_screen")
    data object HomeScreen: Screen(route = "home_screen")
    data object LessonListScreen: Screen(route = "main_screen")
    data object CameraSetupScreen: Screen(route = "camera_setup_screen")
    data object DefaultCameraScreen: Screen(route = "default_camera_screen")
    data object CameraRationaleScreen: Screen(route = "camera_rationale_screen")
    data object LiveStreamCameraScreen: Screen(route = "live_stream_camera_screen")
}