package com.ruralnative.handsy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruralnative.handsy.ui.entryUI.EntryScreen
import com.ruralnative.handsy.ui.entryUI.EntryViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = "entry_screen"
    ) {
        composable(Screen.Entry.route) {
            val viewModel = hiltViewModel<EntryViewModel>()
            EntryScreen(
                modifier = Modifier,
                navController = navHostController,
                viewModel = viewModel)
        }
        composable(Screen.UserIntro.route) {
            TODO("Add code to direct to UserIntroScreen")
        }
        composable(Screen.Author.route) {
            TODO("Add code to direct to AuthorMessageScreen")
        }
    }

}