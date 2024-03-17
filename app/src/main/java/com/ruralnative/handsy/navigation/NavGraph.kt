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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ruralnative.handsy.ui.compose.CameraScreen
import com.ruralnative.handsy.ui.compose.DevsIntroScreen
import com.ruralnative.handsy.ui.compose.EntryScreen
import com.ruralnative.handsy.ui.compose.UserIntroScreen
import com.ruralnative.handsy.ui.compose.LessonScreen
import com.ruralnative.handsy.ui.viewmodel.LessonViewModel
import com.ruralnative.handsy.ui.compose.MainScreen

private const val TAG = "NavGraph"

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
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    ),
                )
            }
        ) {
            EntryScreen(
                viewModel = hiltViewModel(),
                onNavigateToUserIntro = {
                    navController.navigate(Screen.UserIntro.route)
                },
                onNavigateToMain = {
                    navController.navigate(Screen.UserIntro.route)
                    //navController.navigate(Screen.MainScreen.route)
                }
            )
        }

        /**
         * User Intro
         */
        composable(
            route = Screen.UserIntro.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        500, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(150, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        500, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(75, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
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
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        500, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(150, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        500, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(75, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            DevsIntroScreen(
                onButtonClick = {
                    navController.navigate(Screen.MainScreen.route)
                }
            )
        }

        // Main Screen Composable
        composable(
            route = Screen.MainScreen.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                )
            }
        ) {
            Log.d(TAG, "MainScreen INITIALIZED")
            MainScreen(
                navigateToLessonScreen = { id ->
                    navController.navigate("lesson_screen/$id")
                },
                navController
            )
        }

        // Lesson Screen Composable
        composable(
            "lesson_screen/{lessonID}",
            arguments = listOf(
                navArgument("lessonID") {
                    defaultValue = 1
                    type = NavType.IntType
                }
            ),
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {backStackEntry ->
            val viewModel: LessonViewModel = hiltViewModel(backStackEntry)
            LessonScreen(viewModel)
        }

        // Camera Screen Composable
        composable(
            route = Screen.CameraScreen.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            Log.d(TAG, "CameraScreen INITIALIZED")
            CameraScreen()
        }

        // Stats Screen Composable
        composable(
            route = Screen.StatsScreen.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            Log.d(TAG, "StatsScreen INITIALIZED")
        }
    }

}