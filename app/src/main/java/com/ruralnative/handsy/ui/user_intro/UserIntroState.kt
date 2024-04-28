package com.ruralnative.handsy.ui.user_intro

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationState

/**
 * Represents the state of the user introduction screen.
 * This class holds the current state of the user name input, visibility states of UI elements,
 * and a flag indicating if the entered name is incompatible.
 *
 * @property userNameState The current state of the user name input. Defaults to a single space.
 * @property isNameIncompatible A boolean flag indicating if the entered name is incompatible. Defaults to false.
 * @property headerVisibility A boolean flag indicating the visibility of the header. Defaults to false.
 * @property imageVisibility A boolean flag indicating the visibility of the image. Defaults to false.
 * @property textFieldVisibility A boolean flag indicating the visibility of the text field. Defaults to false.
 */
data class UserIntroState(
    val userNameState: String = " ",
    val isNameIncompatible: Boolean = false,
    val headerVisibility: Boolean = false,
    val imageVisibility: Boolean = false,
    val textFieldVisibility: Boolean = false
)
