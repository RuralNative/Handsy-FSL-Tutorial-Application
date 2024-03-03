package com.ruralnative.handsy.ui.cameraScreen

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    viewModel: CameraViewModel = hiltViewModel()
) {
    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    CameraPermission(permissionState)
    SideEffect {
        permissionState.launchPermissionRequest()
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CameraPermission(
    permissionState: PermissionState,
) {
    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = { /* ... */ },
        permissionNotAvailableContent = { /* ... */ }
    ) {
        Camera()
    }
}

@Composable
private fun Camera() {
    TODO("Initialize CameraX")
}