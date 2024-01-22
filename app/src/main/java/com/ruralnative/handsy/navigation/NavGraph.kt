package com.ruralnative.handsy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruralnative.handsy.ui.entryUI.EntryScreen
import com.ruralnative.handsy.ui.entryUI.EntryViewModel

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
                    navController.navigate(Screen.Author.route)
                }
            )
        }
        composable(Screen.UserIntro.route) {
            TODO("Add code to direct to UserIntroScreen")
        }
        composable(Screen.Author.route) {
            TODO("Add code to direct to AuthorMessageScreen")
        }
    }

}