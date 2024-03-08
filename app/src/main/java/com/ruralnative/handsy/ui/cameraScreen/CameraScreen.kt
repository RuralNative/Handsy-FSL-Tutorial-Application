package com.ruralnative.handsy.ui.cameraScreen

import android.Manifest
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

/**
 * Builds a Composable Screen with a built-in CameraX object and MediaPipe integration
 * @param viewModel Hilt-injected ViewModel object responsible for preparing and managing the data for the Composable screen
 */
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

/**
 * Initializes permission request event for camera use and creates and builds a CameraX composable. Depending on whether the application is granted permission to use the device camera by the user, the CameraX can either show or instead revert to a black screen
 * @param permissionState PermissionState object hoisted to observe permission status provided by user
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun CameraPermission(
    permissionState: PermissionState
) {
    PermissionRequired(
        permissionState = permissionState,
        permissionNotGrantedContent = {
            SideEffect {
                permissionState.launchPermissionRequest()
            }
        },
        permissionNotAvailableContent = { /* ... */ }
    ) {
        CameraComponent(
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * Creates a Compose-compatible CameraX object. Note that the Camera only uses the front camera
 * @param modifier Modifier object that determines the style and design of the CameraX composable container
 */
@Composable
private fun CameraComponent(
    modifier: Modifier
) {
    val context = LocalContext.current
    val previewView: PreviewView = remember { PreviewView(context) }
    val cameraController = remember { LifecycleCameraController(context) }
    val lifecycleOwner = LocalLifecycleOwner.current
    cameraController.bindToLifecycle(lifecycleOwner)
    cameraController.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
    previewView.controller = cameraController

    Box(modifier) {
        AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())
    }
}