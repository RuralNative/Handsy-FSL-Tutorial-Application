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
            val viewModel = hiltViewModel<EntryViewModel>()
            EntryScreen(
                modifier = Modifier,
                viewModel = viewModel,
                onNavigateToUser = {
                    navController.navigate(Screen.UserIntro.route)
                },
                onNavigateToMain = {
                    //Navigate to Main
                }
            )
        }
        composable(Screen.UserIntro.route) {
            val viewModel = hiltViewModel<UserIntroViewModel>()
            UserIntroScreen(
                modifier = Modifier,
                navigateToMainScreen = {
                    navController.navigate(Screen.MainScreen.route)
                },
                viewModel = viewModel )
        }
    }

}