package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition

import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.concurrent.Executors

@Composable
fun CameraGestureRecognition(
    viewModel: CameraGestureRecognitionViewModel = hiltViewModel()
) {
    val currentContext = LocalContext.current
    val currentConfiguration = LocalConfiguration.current
    val currentLifecycleOwner = LocalLifecycleOwner.current

    val cameraView = remember {
        PreviewView(currentContext)
    }

    LaunchedEffect(Unit) {
        viewModel.setupCameraProvider(
            currentContext,
            currentConfiguration,
            currentLifecycleOwner,
            cameraView
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                cameraView
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}






