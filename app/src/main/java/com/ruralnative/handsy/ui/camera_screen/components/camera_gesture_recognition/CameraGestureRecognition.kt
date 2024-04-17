package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner

@Composable
fun CameraGestureRecognition(
    viewModel: CameraGestureRecognitionViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val currentLifecycleOwner = LocalLifecycleOwner.current

}

@Composable
private fun CameraProviderSetup(
    context: Context,
    currentLifecycleOwner: LifecycleOwner
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    cameraProviderFuture.addListener(
        {
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build()

            val imageAnalysis = ImageAnalysis.Builder()
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                .build()

            cameraProvider.bindToLifecycle(
                currentLifecycleOwner, cameraSelector, preview, imageAnalysis
            )

            preview.setSurfaceProvider(
                PreviewView(context).surfaceProvider
            )
        },
        ContextCompat.getMainExecutor(context)
    )
}






