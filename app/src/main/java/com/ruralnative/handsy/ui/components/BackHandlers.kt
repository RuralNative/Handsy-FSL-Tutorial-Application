package com.ruralnative.handsy.ui.components

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
public fun DefaultBackHandler(
    backHandlerState: Boolean
) {
    val application = LocalContext.current as ComponentActivity
    BackHandler(backHandlerState) {
        application.finish()
        TODO("Prompt a Dialog with Option to Exit App")
    }
}

@Composable
public fun DisabledBackHandler(
    backHandlerState: Boolean
) {
    BackHandler(backHandlerState) {
        true
    }
}

@Composable
public fun ScaffoldBackHandler(
    backHandlerState: Boolean,
    onBackNavigation: () -> Unit
) {
    BackHandler(backHandlerState) {
        TODO("Prompt a Dialog with Option to LessonList Screen")
    }
}

@Composable
public fun LessonBackHandler(
    backHandlerState: Boolean,
    onBackNavigation: () -> Unit
) {
    BackHandler(backHandlerState) {
        TODO("Prompt a Dialog with Option to LessonList Screen")
    }
}