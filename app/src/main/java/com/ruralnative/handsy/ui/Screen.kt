package com.ruralnative.handsy.ui

/*
This sealed class Screen represents the different Composable screens in the application, restricting the class hierarchy to what is defined here, allowing handling of navigation results to be easier.
 */
sealed class Screen(val route: String) {
    data object LoadingScreen: Screen(route = "loading_screen")
    data object UserIntroScreen: Screen(route = "user_intro_screen")
    data object AuthorMessageScreen: Screen(route = "author_message_screen")
}