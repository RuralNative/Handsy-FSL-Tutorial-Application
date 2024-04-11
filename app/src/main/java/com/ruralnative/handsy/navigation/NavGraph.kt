package com.ruralnative.handsy.navigation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.MISCELLANEOUS_ENTER_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.MISCELLANEOUS_EXIT_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.PRELIMINARY_ENTER_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.PRELIMINARY_EXIT_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.SCAFFOLD_ENTER_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.SCAFFOLD_EXIT_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.TAG
import com.ruralnative.handsy.navigation.data.Screen
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.camera_screen.components.CameraScreen
import com.ruralnative.handsy.ui.components.HandsyScaffold
import com.ruralnative.handsy.ui.dev_intro.DevsIntroScreen
import com.ruralnative.handsy.ui.entry.EntryScreen
import com.ruralnative.handsy.ui.home_screen.HomeScreen
import com.ruralnative.handsy.ui.user_intro.UserIntroScreen
import com.ruralnative.handsy.ui.lesson_screen.LessonScreen
import com.ruralnative.handsy.ui.lesson_screen.LessonViewModel
import com.ruralnative.handsy.ui.lesson_list_screen.LessonListScreen

/**
 * Composable function for the main navigation graph.
 * This composable defines the navigation graph for the application.
 * This function is used to set the proper UI content through the ComponentActivity.setContent{},
 * initializing first through the EntryScreen as its starting destination.
 *
 * @param navController The NavHostController for this navigation graph.
 */
@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "entry_screen",
        enterTransition = { EnterTransition.None},
        exitTransition = { ExitTransition.None }
    ) {

        // Entry Route
        composable(
            route = Screen.Entry.route,
            exitTransition = PRELIMINARY_EXIT_TRANSITION
        ) {
            HandsyTheme {
                EntryScreen(
                    viewModel = hiltViewModel(),
                    onNavigateToUserIntro = {
                        navController.navigate(Screen.UserIntro.route) {
                            popUpTo(Screen.Entry.route) {
                                inclusive = true
                            }
                        }
                    },
                    onNavigateToMain = {
                        navController.navigate(Screen.LessonListScreen.route) {
                            popUpTo(Screen.Entry.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        // User Intro Route
        composable(
            route = Screen.UserIntro.route,
            enterTransition = PRELIMINARY_ENTER_TRANSITION,
            exitTransition = PRELIMINARY_EXIT_TRANSITION
        ) {
            HandsyTheme {
                UserIntroScreen(
                    viewModel = hiltViewModel(),
                    onNavigateToDevsIntro = {
                        navController.navigate(Screen.DevsIntro.route) {
                            popUpTo(Screen.UserIntro.route) {
                                inclusive = true
                            }
                        }
                    }
                )

            }
        }

        // Devs Intro
        composable(
            route = Screen.DevsIntro.route,
            enterTransition = PRELIMINARY_ENTER_TRANSITION,
            exitTransition = PRELIMINARY_EXIT_TRANSITION
        ) {
            HandsyTheme {
                DevsIntroScreen(
                    onNavigateToHome = {
                        navController.navigate(Screen.HomeScreen.route) {
                            popUpTo(Screen.DevsIntro.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }

        // HomeScreen
        composable(
            route = Screen.HomeScreen.route,
            enterTransition = SCAFFOLD_ENTER_TRANSITION,
            exitTransition = SCAFFOLD_EXIT_TRANSITION
        ) {
            HandsyScaffold(
                navController.currentBackStackEntryAsState().value?.destination,
                { navController.navigate(Screen.LessonListScreen.route) },
                { navController.navigate(Screen.HomeScreen.route) },
                { navController.navigate(Screen.CameraSetupScreen.route) }
            ) {
                Surface(
                    modifier = Modifier
                        .consumeWindowInsets(it)
                        .fillMaxSize()
                ) {
                    HomeScreen()
                }
            }
        }

        // LessonListScreen
        composable(
            route = Screen.LessonListScreen.route,
            enterTransition = SCAFFOLD_ENTER_TRANSITION,
            exitTransition = SCAFFOLD_EXIT_TRANSITION
        ) {
            HandsyScaffold(
                navController.currentBackStackEntryAsState().value?.destination,
                { navController.navigate(Screen.LessonListScreen.route) },
                { navController.navigate(Screen.HomeScreen.route) },
                { navController.navigate(Screen.CameraSetupScreen.route) }
            ) {
                Surface(
                    modifier = Modifier
                        .consumeWindowInsets(it)
                        .fillMaxSize()
                ) {
                    LessonListScreen(
                        navigateToLessonScreen = { id ->
                            navController.navigate("lesson_screen/$id")
                        }
                    )
                }
            }
        }

        /*
         * CameraSetup Screen
         */
        composable(
            route = Screen.CameraSetupScreen.route,
            enterTransition = SCAFFOLD_ENTER_TRANSITION,
            exitTransition = SCAFFOLD_EXIT_TRANSITION
        ) {
            HandsyScaffold(
                navController.currentBackStackEntryAsState().value?.destination,
                { navController.navigate(Screen.LessonListScreen.route) },
                { navController.navigate(Screen.HomeScreen.route) },
                { navController.navigate(Screen.CameraSetupScreen.route) }
            ) {
                Surface(
                    modifier = Modifier
                        .consumeWindowInsets(it)
                        .fillMaxSize()
                ) {
                    CameraScreen()
                }
            }
        }

        /*
         * Lesson Screen
         */
        composable(
            "lesson_screen/{lessonID}",
            arguments = listOf(
                navArgument("lessonID") {
                    defaultValue = 1
                    type = NavType.IntType
                }
            ),
            enterTransition = MISCELLANEOUS_ENTER_TRANSITION,
            exitTransition = MISCELLANEOUS_EXIT_TRANSITION
        ) { backStackEntry ->
            val viewModel: LessonViewModel = hiltViewModel(backStackEntry)
            LessonScreen(viewModel)
        }

        /*
         * DefaultCamera Screen
         */
        composable(
            route = Screen.DefaultCameraScreen.route,
            enterTransition = MISCELLANEOUS_ENTER_TRANSITION,
            exitTransition = MISCELLANEOUS_EXIT_TRANSITION
        ) {
            Log.d(TAG, "DefaultCamera Screen INITIALIZED")
        }

        /*
         * CameraRationale Screen
         */
        composable(
            route = Screen.CameraRationaleScreen.route,
            enterTransition = MISCELLANEOUS_ENTER_TRANSITION,
            exitTransition = MISCELLANEOUS_EXIT_TRANSITION
        ) {
            Log.d(TAG, "CameraRationale Screen INITIALIZED")
        }

        /*
         * LiveStreamCamera Screen
         */
        composable(
            route = Screen.LiveStreamCameraScreen.route,
            enterTransition = MISCELLANEOUS_ENTER_TRANSITION,
            exitTransition = MISCELLANEOUS_EXIT_TRANSITION
        ) {
            Log.d(TAG, "LiveStreamCamera Screen INITIALIZED")
        }
    }
}
