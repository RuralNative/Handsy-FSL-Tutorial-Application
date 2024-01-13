package com.ruralnative.handsy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruralnative.handsy.ui.entryUI.EntryScreen
import com.ruralnative.handsy.ui.initialScreens.UserIntroScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Entry.route
    ) {
        composable(Screen.Entry.route) { EntryScreen(navController)}
        //composable(Screen.UserIntro.route) {UserIntroScreen(navController)}
        //Add composable() for AuthorMessageScreen
    }
}