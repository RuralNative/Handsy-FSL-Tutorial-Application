package com.ruralnative.handsy.navigation

sealed class Screen(val route: String) {
    data object Entry: Screen(route = "entry_screen")
    data object UserIntro: Screen(route = "user_intro_screen")
    data object MainScreen: Screen(route = "main_screen")
    data object LessonScreen: Screen(route = "lesson_screen/{lessonID}")
    data object CameraScreen: Screen(route = "camera_screen")
    data object StatsScreen: Screen(route = "stats_screen")
}