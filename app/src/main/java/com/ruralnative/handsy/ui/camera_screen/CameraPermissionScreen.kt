package com.ruralnative.handsy.ui.camera_screen

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import kotlinx.coroutines.delay

@Composable
@OptIn(ExperimentalPermissionsApi::class)
fun CameraPermissionRequest(
    viewModel: CameraPermissionViewModel,
    onNavigateToDefaultScreen: () -> Unit,
    onNavigateToRationaleScreen: () -> Unit,
    onNavigateToCameraScreen: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )

    LaunchedEffect(cameraPermissionState) {
        when (cameraPermissionState.status) {
            is PermissionStatus.Granted -> {
                viewModel.setCameraPermissionKey(2)
            }
            is PermissionStatus.Denied -> {
                if (cameraPermissionState.status.shouldShowRationale) {
                    viewModel.setCameraPermissionKey(1)
                } else {
                    viewModel.setCameraPermissionKey(0)
                }
            }
        }
        viewModel.initializeCameraScreen(
            onNavigateToDefaultScreen,
            onNavigateToRationaleScreen,
            onNavigateToCameraScreen
        )
    }
}