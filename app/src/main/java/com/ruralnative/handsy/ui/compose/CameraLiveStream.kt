package com.ruralnative.handsy.ui.compose

import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.ui.viewmodel.CameraLiveStreamViewModel

@Composable
fun CameraLiveStream(
    viewModel: CameraLiveStreamViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val gestureAnalyzer = viewModel.initializeAnalyzer(context)
    val controller = viewModel.initializeCameraController(context, gestureAnalyzer)
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        factory = {
            PreviewView(it).apply {
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
            }
        }
    )
}