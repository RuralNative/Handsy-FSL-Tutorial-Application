package com.ruralnative.handsy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navController: NavController
) {
    NavHost(
        navController = navController,
        startDestination = "entry_screen"
    ) {
        composable("entry_screen") {

        }
    }
}