package com.ruralnative.handsy.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale


@Composable
@OptIn(ExperimentalPermissionsApi::class)
private fun CameraPermissionRequest(
    cameraScreen: @Composable () -> Unit
) {

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    LaunchedEffect(cameraPermissionState) {
        when (cameraPermissionState.status) {
            is PermissionStatus.Granted -> {
                TODO("Run Passed Composables")
            }
            is PermissionStatus.Denied -> {
                if (cameraPermissionState.status.shouldShowRationale) {
                    TODO("Show Rationale through Composable ()")
                } else {
                    TODO("Show Scaffold UI with Direction to Settings")
                }
            }
        }
    }
}

@Composable
private fun PermissionRequestDialog() {
    AlertDialog(
        title = null,
        text = null,
        icon = null,
        onDismissRequest = { /*TODO*/ },
        confirmButton = { /*TODO*/ }
    )
}