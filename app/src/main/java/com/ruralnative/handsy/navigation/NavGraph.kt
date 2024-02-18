package com.ruralnative.handsy.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ruralnative.handsy.ui.entryUI.EntryScreen
import com.ruralnative.handsy.ui.entryUI.EntryViewModel
import com.ruralnative.handsy.ui.initialScreens.UserIntroScreen
import com.ruralnative.handsy.ui.initialScreens.UserIntroViewModel
import com.ruralnative.handsy.ui.lessonScreen.LessonScreen
import com.ruralnative.handsy.ui.lessonScreen.LessonViewModel
import com.ruralnative.handsy.ui.mainScreen.MainScreen

private const val TAG = "NavGraph"

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
            Log.d(TAG, "EntryScreen INITIALIZED")
            EntryScreen(
                modifier = Modifier,
                viewModel = hiltViewModel(),
                onNavigateToUser = {
                    navController.navigate(Screen.UserIntro.route)
                },
                onNavigateToMain = {
                    navController.navigate(Screen.MainScreen.route)
                }
            )
        }
        composable(Screen.UserIntro.route) {
            Log.d(TAG, "UserIntroScreen INITIALIZED")
            UserIntroScreen(
                modifier = Modifier,
                navigateToMainScreen = {
                    navController.navigate(Screen.MainScreen.route)
                }
            )
        }
        composable(Screen.MainScreen.route) {
            Log.d(TAG, "MainScreen INITIALIZED")
            MainScreen(
                navigateToLessonScreen = { id ->
                    navController.navigate("lesson_screen/$id")
                },
                navigateToMainScreen = {
                    navController.navigate(Screen.MainScreen.route)
                },
                navigateToCameraScreen = {
                    navController.navigate(Screen.CameraScreen.route)
                },
                navigateToStatsScreen = {
                    navController.navigate(Screen.StatsScreen.route)
                }
            )
        }
        composable(
            "lesson_screen/{lessonID}",
            arguments = listOf(
                navArgument("lessonID") {
                    defaultValue = 1
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            val viewModel: LessonViewModel = hiltViewModel(backStackEntry)
            LessonScreen(viewModel)
        }
        composable(Screen.CameraScreen.route) {
            Log.d(TAG, "CameraScreen INITIALIZED")
        }
        composable(Screen.StatsScreen.route) {
            Log.d(TAG, "StatsScreen INITIALIZED")
        }
    }

}