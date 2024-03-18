package com.ruralnative.handsy.ui.compose.components

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun BackButtonHandler(
    onExit: () -> Unit
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val activity = LocalContext.current as ComponentActivity
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    DisposableEffect(Unit) {
        val backHandler = onBackPressedDispatcher?.addCallback(
            ExitConfirmationDialog(
                onConfirm = { /*TODO*/ })
        )
        onDispose {

        }
    }

    if (showDialog) {
        ExitConfirmationDialog(
            onConfirm = {
                onExit()
                LocalContext.current as ComponentActivity
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
}

@Composable
fun ExitConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Exit App") },
        text = { Text(text = "Are you sure you want to exit the app?") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }
    )
}