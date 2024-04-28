package com.ruralnative.handsy.ui.user_intro

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationState

/**
 * Represents the state of the user introduction screen.
 * This class holds the current state of the user name input.
 *
 * @property userNameState The current state of the user name input. Defaults to a single space.
 */
data class UserIntroState(
    val userNameState: String = " "
    val headerVisibility: Boolean = false,
    val imageVisibility: Boolean = false,
    val textFieldVisibility: Boolean = false
)
