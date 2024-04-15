package com.ruralnative.handsy.ui.camera_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ruralnative.handsy.ui.camera_screen.components.NoRationale
import com.ruralnative.handsy.ui.camera_screen.components.Rationale

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun CameraSetup(
    viewModel: CameraPermissionViewModel = hiltViewModel(),
    onNavigateToCameraScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    LaunchedEffect(cameraPermissionState) {
        viewModel.setCameraPermissionState(cameraPermissionState)
    }

    when (cameraPermissionState.status) {
        is PermissionStatus.Granted -> {
            LaunchedEffect(Unit) {
                onNavigateToCameraScreen()
            }
        }
        is PermissionStatus.Denied -> {
            if (cameraPermissionState.status.shouldShowRationale) {
                Rationale(viewModel.requestCameraPermission(cameraPermissionState))
            } else {
                NoRationale()
            }
        }
    }
}