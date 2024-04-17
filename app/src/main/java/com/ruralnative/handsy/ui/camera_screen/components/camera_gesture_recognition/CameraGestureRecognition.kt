package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import java.util.concurrent.Executors

@Composable
fun CameraGestureRecognition(
    viewModel: CameraGestureRecognitionViewModel = hiltViewModel()
) {
    val currentContext = LocalContext.current
    val currentConfiguration = LocalConfiguration.current
    val currentLifecycleOwner = LocalLifecycleOwner.current

    viewModel.setupCameraProvider(
        currentContext,
        currentConfiguration,
        currentLifecycleOwner
    )
}






