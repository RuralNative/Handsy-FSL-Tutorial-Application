package com.ruralnative.handsy.navigation

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.MISCELLANEOUS_ENTER_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.MISCELLANEOUS_EXIT_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.PRELIMINARY_ENTER_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.PRELIMINARY_EXIT_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.SCAFFOLD_ENTER_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.SCAFFOLD_EXIT_TRANSITION
import com.ruralnative.handsy.navigation.data.Screen
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.assessment_screen.AssessmentScreen
import com.ruralnative.handsy.ui.assessment_screen.AssessmentState
import com.ruralnative.handsy.ui.camera_screen.CameraSetup
import com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.CameraGestureRecognition
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

    val clearBackStackAndNavigate: (destinationRoute: String) -> Unit = {
        navController.navigate(it) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    val navigateSingleInstanceOf: (destinationRoute: String) -> Unit = {
        navController.navigate(it) {
            this.launchSingleTop = true
        }
    }

    val popCurrentAndNavigateSingleInstanceOf: (destinationRoute: String) -> Unit = {
        navController.navigate(it) {
            this.launchSingleTop = true
            popUpTo(Screen.HomeScreen.route) {
                this.inclusive = false
            }
        }
    }

    val backStackedOriginAndNavigate: (
        destinationRoute: String
    ) -> Unit = {
        navController.navigate(it)
    }

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
            HandsyTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                EntryScreen(
                    viewModel = hiltViewModel(),
                    onNavigateToUserIntro = {
                        clearBackStackAndNavigate(Screen.UserIntro.route)
                    },
                    onNavigateToMain = {
                        clearBackStackAndNavigate(Screen.HomeScreen.route)
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
            HandsyTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                UserIntroScreen(
                    viewModel = hiltViewModel(),
                    onNavigateToDevsIntro = {
                        clearBackStackAndNavigate(Screen.DevsIntro.route)
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
            HandsyTheme(
                darkTheme = false,
                dynamicColor = false
            ) {
                DevsIntroScreen(
                    onNavigateToHome = {
                        clearBackStackAndNavigate(Screen.HomeScreen.route)
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
                { popCurrentAndNavigateSingleInstanceOf(Screen.LessonListScreen.route) },
                { popCurrentAndNavigateSingleInstanceOf(Screen.HomeScreen.route) },
                { popCurrentAndNavigateSingleInstanceOf(Screen.CameraSetupScreen.route) }
            ) {
                Surface(
                    modifier = Modifier
                        .consumeWindowInsets(it)
                        .fillMaxSize()
                ) {
                    HomeScreen(
                        viewModel = hiltViewModel(),
                        onNavigateToAssessment = { popCurrentAndNavigateSingleInstanceOf(Screen.AssessmentScreen.route) }
                    )

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
                { popCurrentAndNavigateSingleInstanceOf(Screen.LessonListScreen.route) },
                { popCurrentAndNavigateSingleInstanceOf(Screen.HomeScreen.route) },
                { popCurrentAndNavigateSingleInstanceOf(Screen.CameraSetupScreen.route) }
            ) {
                LessonListScreen(
                    modifier = Modifier
                        .padding(it)
                        .consumeWindowInsets(it)
                        .fillMaxSize(),
                    navigateToLessonScreen = { id ->
                        navController.navigate("lesson_screen/$id")
                    }
                )
            }
        }

        composable(
            route = Screen.AssessmentScreen.route,
            enterTransition = MISCELLANEOUS_ENTER_TRANSITION,
            exitTransition = MISCELLANEOUS_EXIT_TRANSITION
        ) {
            AssessmentScreen(
                onNavigateToHome = { popCurrentAndNavigateSingleInstanceOf(Screen.HomeScreen.route) }
            )
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
                { popCurrentAndNavigateSingleInstanceOf(Screen.LessonListScreen.route) },
                { popCurrentAndNavigateSingleInstanceOf(Screen.HomeScreen.route) },
                { popCurrentAndNavigateSingleInstanceOf(Screen.CameraSetupScreen.route) }
            ) {
                HandsyTheme {
                    CameraSetup(
                        modifier = Modifier
                            .padding(it)
                            .consumeWindowInsets(it),
                        onNavigateToCameraScreen = {
                            popCurrentAndNavigateSingleInstanceOf(Screen.LiveStreamCameraScreen.route)
                        }
                    )
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
         * LiveStreamCamera Screen
         */
        composable(
            route = Screen.LiveStreamCameraScreen.route,
            enterTransition = MISCELLANEOUS_ENTER_TRANSITION,
            exitTransition = MISCELLANEOUS_EXIT_TRANSITION
        ) {
            HandsyScaffold(
                navController.currentBackStackEntryAsState().value?.destination,
                { popCurrentAndNavigateSingleInstanceOf(Screen.LessonListScreen.route) },
                { popCurrentAndNavigateSingleInstanceOf(Screen.HomeScreen.route) },
                { popCurrentAndNavigateSingleInstanceOf(Screen.LiveStreamCameraScreen.route) }
            ) {
                HandsyTheme {
                    CameraGestureRecognition(
                        modifier = Modifier
                            .padding(it)
                            .consumeWindowInsets(it)
                    )
                }
            }
        }
    }
}
