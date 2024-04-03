package com.ruralnative.handsy.ui.compose.components

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
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

    if (cameraPermissionState.status.isGranted) {
        cameraScreen()
    } else {
        TODO("Implement UI For Rationale")
        TODO("Request Camera Permission with different Use Cases")
        TODO("If Request returns Yes, show cameraScreen()")
        TODO("If Request returns No, show CameraPermissionDenied()")
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