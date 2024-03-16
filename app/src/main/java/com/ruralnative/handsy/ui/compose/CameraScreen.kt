package com.ruralnative.handsy.ui.compose

import android.Manifest
import androidx.camera.core.CameraSelector
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ruralnative.handsy.ui.viewmodel.CameraViewModel

/**
 * Builds a Composable Screen with a built-in CameraX object and MediaPipe integration
 * @param viewModel Hilt-injected ViewModel object responsible for preparing and managing the data for the Composable screen
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    viewModel: CameraViewModel = hiltViewModel()
) {
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    )
    CameraPermission(permissionState)
    LaunchedEffect(Unit) {
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
    if (permissionState.status.isGranted) {
        CameraComponent(modifier = Modifier)
    } else {
        Column {
            val textToShow = if (permissionState.status.shouldShowRationale) {
                "The camera is important for this app. Please grant the permission."
            } else {
                // If it's the first time the user lands on this feature, or the user
                // doesn't want to be asked again for this permission, explain that the
                // permission is required
                "Camera permission required for this feature to be available. " +
                        "Please grant the permission"
            }
            Text(textToShow)
            Button(onClick = { permissionState.launchPermissionRequest() }) {
                Text("Request permission")
            }
        }
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