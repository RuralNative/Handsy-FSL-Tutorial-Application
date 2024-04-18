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
import androidx.navigation.navArgument
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.MISCELLANEOUS_ENTER_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.MISCELLANEOUS_EXIT_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.PRELIMINARY_ENTER_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.PRELIMINARY_EXIT_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.SCAFFOLD_ENTER_TRANSITION
import com.ruralnative.handsy.navigation.data.NavGraphTransitions.SCAFFOLD_EXIT_TRANSITION
import com.ruralnative.handsy.navigation.data.Screen
import com.ruralnative.handsy.ui.HandsyTheme
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

    /**
     * Navigates to a specified destination route, optionally popping the back stack up to a current route.
     * This is specifically made for navigations requiring origin to be popped from the backstack,
     * despite having an optional currentRoute as a parameter.
     * currentRoute is optional/nullable to allow convenient use of provided 'route'
     * within the composable() where this value is used.
     *
     * @param currentRoute The current route to pop up to before navigating. Despite its nullable nature,
     * this parameter is recommended to be filled in as this is necessary for its proper call
     * @param destinationRoute The destination route to navigate to.
     */
    val poppedOriginAndNavigateTo: (
        currentRoute: String?,
        destinationRoute: String
    ) -> Unit = { current: String?, destination: String ->
        navController.navigate(destination) {
            if (current != null) {
                popUpTo(current) {
                    inclusive = true
                }
            }
        }
    }

    /**
     * Navigates to a specified destination route without modifying the back stack.
     *
     * @param destinationRoute The destination route to navigate to.
     */
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
            HandsyTheme {
                EntryScreen(
                    viewModel = hiltViewModel(),
                    onNavigateToUserIntro = {
                        poppedOriginAndNavigateTo(route, Screen.UserIntro.route)
                    },
                    onNavigateToMain = {
                        poppedOriginAndNavigateTo(route, Screen.HomeScreen.route)
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
                        poppedOriginAndNavigateTo(route, Screen.DevsIntro.route)
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
                        poppedOriginAndNavigateTo(route, Screen.HomeScreen.route)
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
                { backStackedOriginAndNavigate(Screen.LessonListScreen.route) },
                { poppedOriginAndNavigateTo(route, Screen.HomeScreen.route) },
                { backStackedOriginAndNavigate(Screen.CameraSetupScreen.route) }
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
                { poppedOriginAndNavigateTo(route, Screen.LessonListScreen.route) },
                { poppedOriginAndNavigateTo(route, Screen.HomeScreen.route) },
                { poppedOriginAndNavigateTo(route, Screen.CameraSetupScreen.route) }
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
                { poppedOriginAndNavigateTo(route, Screen.LessonListScreen.route) },
                { poppedOriginAndNavigateTo(route, Screen.HomeScreen.route) },
                { poppedOriginAndNavigateTo(route, Screen.CameraSetupScreen.route) }
            ) {
                HandsyTheme {
                    CameraSetup(
                        modifier = Modifier
                            .padding(it)
                            .consumeWindowInsets(it),
                        onNavigateToCameraScreen = {
                            poppedOriginAndNavigateTo(route, Screen.LiveStreamCameraScreen.route)
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
            CameraGestureRecognition()
        }
    }
}
