package com.ruralnative.handsy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruralnative.handsy.ui.initialScreens.AuthorMessageScreen
import com.ruralnative.handsy.ui.initialScreens.LoadingScreen
import com.ruralnative.handsy.ui.initialScreens.UserIntroScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoadingScreen.route
    ) {
        composable(
            route = Screen.LoadingScreen.route
        ) {
            LoadingScreen()
        }
        composable(
            route = Screen.UserIntroScreen.route
        ) {
            UserIntroScreen()
        }
        composable(
            route = Screen.AuthorMessageScreen.route
        ) {
            AuthorMessageScreen()
        }
    }
}
