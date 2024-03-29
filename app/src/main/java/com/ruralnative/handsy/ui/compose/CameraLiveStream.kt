package com.ruralnative.handsy.ui.compose

import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.ruralnative.handsy.util.GestureRecognizerHelper

@Composable
fun CameraLiveStream(
    modifier: Modifier = Modifier
) {
    var classifications by remember {
        mutableStateOf(emptyList<GestureRecognizerResult>())
    }
    val analyzer = remember {
        GestureRecognizerHelper(
            context = LocalContext.current,
            onResults = {
                classifications = it
            }
        )
    }

    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context),
                TODO("Complete ImageAnalysisAnalyzer")
        }
    }
    controller.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        factory = {
            PreviewView(it).apply {
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
            }
        },
        modifier = modifier
    )
}