package com.ruralnative.handsy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruralnative.handsy.ui.entryUI.EntryScreen
import com.ruralnative.handsy.ui.entryUI.EntryViewModel
import com.ruralnative.handsy.ui.initialScreens.UserIntroScreen
import com.ruralnative.handsy.ui.initialScreens.UserIntroViewModel
import com.ruralnative.handsy.ui.mainScreen.MainScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = "entry_screen"
    ) {
        composable(Screen.Entry.route) {
            EntryScreen(
                modifier = Modifier,
                viewModel = hiltViewModel<EntryViewModel>(),
                onNavigateToUser = {
                    navController.navigate(Screen.UserIntro.route)
                },
                onNavigateToMain = {
                    navController.navigate(Screen.MainScreen.route)
                }
            )
        }
        composable(Screen.UserIntro.route) {
            UserIntroScreen(
                modifier = Modifier,
                viewModel = hiltViewModel<UserIntroViewModel>(),
                navigateToMainScreen = {
                    navController.navigate(Screen.MainScreen.route)
                }
            )
        }
        composable(Screen.MainScreen.route) {
            MainScreen()
        }
    }

}