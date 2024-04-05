package com.ruralnative.handsy.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.compose.composable
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.ruralnative.handsy.ui.components.HandsyScaffold
import com.ruralnative.handsy.ui.dev_intro.DevsIntroScreen
import com.ruralnative.handsy.ui.entry.EntryScreen
import com.ruralnative.handsy.ui.user_intro.UserIntroScreen
import com.ruralnative.handsy.ui.lesson_screen.LessonScreen
import com.ruralnative.handsy.ui.lesson_screen.LessonViewModel
import com.ruralnative.handsy.ui.main_screen.LessonListScreen

private const val TAG = "NavGraph"
private val PRELIMINARY_ENTER_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    fadeIn(
        animationSpec = tween(
            150, easing = LinearEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(150, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )
}
private val PRELIMINARY_EXIT_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
    ExitTransition = {
    fadeOut(
        animationSpec = tween(
            150, easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(150, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )
}
private val SCAFFOLD_ENTER_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    fadeIn(
        animationSpec = tween(
            150, easing = LinearEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(150, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )
}
private val SCAFFOLD_EXIT_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
ExitTransition = {
    fadeOut(
        animationSpec = tween(
            75, easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(75, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )
}
private val MISCELLANEOUS_ENTER_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    fadeIn(
        animationSpec = tween(
            150, easing = LinearEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(150, easing = EaseIn),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )
}
private val MISCELLANEOUS_EXIT_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
ExitTransition = {
    fadeOut(
        animationSpec = tween(
            150, easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(150, easing = EaseOut),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )
}

/**
 * Composable function for the main navigation graph.
 * This composable defines the navigation graph for the application, including the entry screen, user intro screen, main screen, lesson screen, camera screen, and stats screen.
 * @param navController tThe NavHostController for this navigation graph.
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

        /**
         * Entry Screen
         */
        composable(
            route = Screen.Entry.route,
            exitTransition = PRELIMINARY_EXIT_TRANSITION
        ) {
            EntryScreen(
                viewModel = hiltViewModel(),
                onNavigateToUserIntro = {
                    navController.navigate(Screen.UserIntro.route)
                },
                onNavigateToMain = {
                    navController.navigate(Screen.LessonListScreen.route)
                }
            )
        }

        /**
         * User Intro
         */
        composable(
            route = Screen.UserIntro.route,
            enterTransition = PRELIMINARY_ENTER_TRANSITION,
            exitTransition = PRELIMINARY_EXIT_TRANSITION
        ) {
            Log.d(TAG, "UserIntroScreen INITIALIZED")
            UserIntroScreen(
                onNavigateToDevsIntro = {
                    navController.navigate(Screen.DevsIntro.route)
                }
            )
        }

        /**
         * Devs Intro composable
         */
        composable(
            route = Screen.DevsIntro.route,
            enterTransition = PRELIMINARY_ENTER_TRANSITION,
            exitTransition = PRELIMINARY_EXIT_TRANSITION
        ) {
            DevsIntroScreen(
                onButtonClick = {
                    navController.navigate(Screen.LessonListScreen.route)
                }
            )
        }

        /*
         * Home Screen
         */
        composable(
            route = Screen.HomeScreen.route,
            enterTransition = SCAFFOLD_ENTER_TRANSITION,
            exitTransition = SCAFFOLD_EXIT_TRANSITION
        ) {
            Log.d(TAG, "HomeScreen INITIALIZED")
        }

        /*
         * LessonList Screen
         */
        composable(
            route = Screen.LessonListScreen.route,
            enterTransition = SCAFFOLD_ENTER_TRANSITION,
            exitTransition = SCAFFOLD_EXIT_TRANSITION
        ) {
            Log.d(TAG, "LessonListScreen INITIALIZED")
            HandsyScaffold(navigationController = navController) {
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
         * CameraSetup Screen
         */
        composable(
            route = Screen.CameraSetupScreen.route,
            enterTransition = SCAFFOLD_ENTER_TRANSITION,
            exitTransition = SCAFFOLD_EXIT_TRANSITION
        ) {
            Log.d(TAG, "CameraSetup Screen INITIALIZED")
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
