package com.ruralnative.handsy.ui.components

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
public fun DefaultBackHandler(
    backHandlerState: Boolean
) {
    val application = LocalContext.current as ComponentActivity
    BackHandler(backHandlerState) {
        AlertDialog(
            icon = {
                Icon(icon, contentDescription = "Example Icon")
            },
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(text = dialogText)
            },
            onDismissRequest = {
                onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
        application.finish()
        TODO("Prompt a Dialog with Option to Exit App")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DefaultDialog(
) {
    AlertDialog(
        icon = {
            Icon(
                icon,
                contentDescription = "Example Icon"
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            TODO("On Dismiss Request")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    TODO("Confirm Button")
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    TODO("Dismiss Button")
                }
            ) {
                Text("Dismiss")
            }
        }
    )
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

@Composable
public fun DisabledBackHandler(
    backHandlerState: Boolean
) {
    BackHandler(backHandlerState) {
        true
    }
}
