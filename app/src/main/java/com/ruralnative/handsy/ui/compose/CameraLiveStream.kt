package com.ruralnative.handsy.ui.compose

import android.Manifest
import android.content.Context
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ruralnative.handsy.ui.compose.components.TopBar
import com.ruralnative.handsy.ui.viewmodel.CameraLiveStreamViewModel
import com.ruralnative.handsy.util.GestureRecognizerClassifier

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraLiveStream(
    viewModel: CameraLiveStreamViewModel = hiltViewModel()
) {

    val permissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA
    )
    LivestreamCameraPermission(
        permissionState,
        viewModel
    )
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val gestureAnalyzer = viewModel.initializeAnalyzer(context)
    val controller = viewModel.initializeCameraController(context, gestureAnalyzer)
    val lifecycleOwner = LocalLifecycleOwner.current

    TopBar()
    AndroidView(
        factory = {
            createPreviewView(
                context = it,
                controller = controller,
                lifecycleOwner = lifecycleOwner
            )
        }
    )
}

/**
 * Initializes permission request event for camera use and creates and builds a CameraX composable. Depending on whether the application is granted permission to use the device camera by the user, the CameraX can either show or instead revert to a black screen
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun LivestreamCameraPermission(
    permissionState: PermissionState,
    viewModel: CameraLiveStreamViewModel
) {
    if (permissionState.status.isGranted) {
        CameraComponent(
            modifier = Modifier,
            viewModel = hiltViewModel()
        )
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
    modifier: Modifier,
    viewModel: CameraLiveStreamViewModel
) {
    val context = LocalContext.current
    val gestureAnalyzer = viewModel.initializeAnalyzer(context)
    val controller = viewModel.initializeCameraController(context, gestureAnalyzer)
    val lifecycleOwner = LocalLifecycleOwner.current

    Box(modifier) {
        TopBar()
        AndroidView(
            factory = {
                createPreviewView(
                    context = it,
                    controller = controller,
                    lifecycleOwner = lifecycleOwner
                )
            },
            modifier = Modifier
                .fillMaxSize()
        )
    }


}

private fun createPreviewView(
    context: Context,
    controller: LifecycleCameraController,
    lifecycleOwner: LifecycleOwner
): PreviewView {
    return PreviewView(context).apply {
        this.controller = controller
        controller.bindToLifecycle(lifecycleOwner)
    }
}