package com.ruralnative.handsy.ui.cameraScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun CameraScreen(
    viewModel: CameraViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val cameraPermissionGranted = uiState.cameraPermissionGranted

    viewModel.checkAndUpdatePermission(LocalContext.current)

    if (cameraPermissionGranted) {
        TODO("Show Camera Preview")
    } else {
        TODO("Request camera permission and show dialog where permission is not granted")
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionRequest() {
    // Create a permission state for the camera permission
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    when (cameraPermissionState.status) {
        // If the permission is granted, you can use the camera feature
        PermissionStatus.Granted -> {
            Text("Camera permission Granted")
            // Add your camera feature UI here
        }
        is PermissionStatus.Denied -> {
            // If the permission is denied, show a message and a button to request the permission
            Column {
                Text(if (cameraPermissionState.status.shouldShowRationale) {
                    "The camera is important for this app. Please grant the permission."
                } else {
                    "Camera permission required for this feature to be available. Please grant the permission."
                })
                Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                    Text("Request permission")
                }
            }
        }
    }
}

