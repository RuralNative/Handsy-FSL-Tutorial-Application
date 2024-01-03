package com.ruralnative.handsy.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruralnative.handsy.ui.initialScreens.LoadingScreen

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
    }
}
