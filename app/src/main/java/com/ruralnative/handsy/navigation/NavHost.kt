package com.ruralnative.handsy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "entry_screen"
    ) {
        composable(Screen.Entry.route) {
            TODO("Add code to direct to EntryScreen")
        }
        composable(Screen.UserIntro.route) {
            TODO("Add code to direct to UserIntroScreen")
        }
        composable(Screen.Author.route) {
            TODO("Add code to direct to AuthorMessageScreen")
        }
    }
}