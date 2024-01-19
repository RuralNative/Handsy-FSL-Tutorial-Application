package com.ruralnative.handsy.navigation

sealed class Screen(val route: String) {
    data object Entry: Screen(route = "entry_screen")
    data object UserIntro: Screen(route = "user_intro_screen")
    data object Author: Screen(route = "author_message_screen")
}