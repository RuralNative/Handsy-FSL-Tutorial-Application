package com.ruralnative.handsy.ui.compose

import android.content.Context
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
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

    DisposableEffect(Unit) {
        onDispose {
            controller.unbind()
        }
    }

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