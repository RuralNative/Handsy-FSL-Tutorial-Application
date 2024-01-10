package com.ruralnative.handsy.ui

sealed class Screen(val route: String) {
    object Entry: Screen(route = "entry_screen")
    object UserIntro: Screen(route = "user_intro_screen")
    object AuthorMessage: Screen(route = "author_message_screen")
}