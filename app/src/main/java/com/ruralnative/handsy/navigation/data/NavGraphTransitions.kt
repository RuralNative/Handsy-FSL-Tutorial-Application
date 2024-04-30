package com.ruralnative.handsy.navigation.data

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

object NavGraphTransitions {

    const val TAG = "NavGraph"

    /**
     * Preliminary enter transition for screens.
     * This transition fades in the screen while sliding it into the container from the start.
     */
    val PRELIMINARY_ENTER_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        fadeIn(
            animationSpec = tween(
                150, easing = LinearEasing
            )
        )
    }

    /**
     * Preliminary exit transition for screens.
     * This transition fades out the screen while sliding it out of the container to the end.
     */
    val PRELIMINARY_EXIT_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
    ExitTransition = {
        fadeOut(
            animationSpec = tween(
                150, easing = LinearEasing
            )
        )
    }

    /**
     * Scaffold enter transition for screens.
     * This transition fades in the screen while sliding it into the container from the start.
     */
    val SCAFFOLD_ENTER_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        fadeIn(
            animationSpec = tween(
                150, easing = LinearEasing
            )
        ) + slideIntoContainer(
            animationSpec = tween(150, easing = EaseIn),
            towards = AnimatedContentTransitionScope.SlideDirection.Start
        )
    }

    /**
     * Scaffold exit transition for screens.
     * This transition fades out the screen while sliding it out of the container to the end.
     */
    val SCAFFOLD_EXIT_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
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

    /**
     * Miscellaneous enter transition for screens.
     * This transition fades in the screen while sliding it into the container from the start.
     */
    val MISCELLANEOUS_ENTER_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        fadeIn(
            animationSpec = tween(
                150, easing = LinearEasing
            )
        ) + slideIntoContainer(
            animationSpec = tween(150, easing = EaseIn),
            towards = AnimatedContentTransitionScope.SlideDirection.Start
        )
    }

    /**
     * Miscellaneous exit transition for screens.
     * This transition fades out the screen while sliding it out of the container to the end.
     */
    val MISCELLANEOUS_EXIT_TRANSITION: AnimatedContentTransitionScope<NavBackStackEntry>.() ->
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
}