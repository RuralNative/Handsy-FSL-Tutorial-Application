package com.ruralnative.handsy.ui.camera_screen

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ruralnative.handsy.ui.HandsyTheme
import com.ruralnative.handsy.ui.camera_screen.components.NoRationale
import com.ruralnative.handsy.ui.camera_screen.components.Rationale

/**
 * A composable screen that sets up the camera responsible for gesture recognition.
 *
 * This composable checks the camera permission status and displays the appropriate UI
 * based on whether the permission is granted, denied, or if a rationale should be shown.
 *
 * @param onNavigateToCameraScreen A lambda function to be called when the camera permission is granted,
 *                                 triggering navigation to the CameraGestureRecognition screen.
 */
@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun CameraSetup(
    modifier: Modifier,
    viewModel: CameraPermissionViewModel = hiltViewModel(),
    onNavigateToCameraScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    LaunchedEffect(cameraPermissionState) {
        viewModel.setCameraPermissionState(cameraPermissionState)
    }

    Surface(modifier = modifier) {
        if (cameraPermissionState.status.isGranted) {
            LaunchedEffect(Unit) {
                onNavigateToCameraScreen()
            }
        } else if (cameraPermissionState.status.shouldShowRationale) {
            Rationale(viewModel.requestCameraPermission(cameraPermissionState))
        } else {
            NoRationale { viewModel.openApplicationSettings(context) }
        }
    }
}