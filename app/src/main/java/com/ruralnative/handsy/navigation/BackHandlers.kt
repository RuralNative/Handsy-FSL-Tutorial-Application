package com.ruralnative.handsy.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.ruralnative.handsy.R

@Composable
public fun DefaultBackHandler(
    backHandlerState: Boolean
) {
    val application = LocalContext.current as ComponentActivity
    var openDialog by remember {
        mutableStateOf(false)
    }

    BackHandler(backHandlerState) {
        openDialog = true
    }

    if (openDialog) {
        DefaultDialog(
            application = application,
            dialogState = openDialog)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DefaultDialog(
    application: ComponentActivity,
    dialogState: Boolean
) {
    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(R.drawable.mascot),
                contentDescription = null
            )
        },
        title = {
            Text(
                text = stringResource(R.string.default_dialog_title),
                fontWeight = FontWeight.ExtraBold
            )
        },
        text = {
            Text(
                text = stringResource(R.string.default_dialog_description),
                fontWeight = FontWeight.ExtraBold
            )
        },
        onDismissRequest = {
            TODO("On Dismiss Request")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    application.finish()
                }
            ) {
                Text(stringResource(R.string.dialog_confirm))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    dialogState = false
                }
            ) {
                Text(stringResource(R.string.dialog_dismiss))
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
