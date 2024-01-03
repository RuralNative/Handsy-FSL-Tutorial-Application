package com.ruralnative.handsy

/*
This sealed class Screen represents the different Composable screens in the application, restricting the class hierarchy to what is defined here, allowing handling of navigation results to be easier.
 */
sealed class Screen {
    object LoadingScreen: Screen()
    object UserIntroScreen: Screen()
    object AuthorMessageScreen: Screen()
}