package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition

import android.content.Context
import android.content.res.Configuration
import android.view.Surface
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai.GestureAnalysisAnalyzer
import com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai.GestureRecognizerHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class CameraGestureRecognitionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow(CameraGestureRecognitionState(results = emptyList()))
    val uiState: StateFlow<CameraGestureRecognitionState> = _uiState.asStateFlow()

    private val backgroundExecutor = Executors.newSingleThreadExecutor()

    fun setupCameraProvider(
        context: Context,
        currentConfiguration: Configuration,
        currentLifecycleOwner: LifecycleOwner
    ) {
        viewModelScope.launch {
            val gestureAnalyzer = setupGestureRecognizerAnalyzer(context)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener(
                {
                    val cameraProvider = cameraProviderFuture.get()

                    val preview = Preview.Builder().build()

                    val imageAnalysis =
                        ImageAnalysis.Builder()
                            .setTargetRotation(determineScreenConfiguration(currentConfiguration))
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                            .build()
                            .also { it ->
                                it.setAnalyzer(backgroundExecutor) {
                                    gestureAnalyzer.analyze(it)
                                }
                            }

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
    }

    private fun setupGestureRecognizerAnalyzer(
        context: Context
    ): GestureAnalysisAnalyzer {
        val gestureRecognizer = GestureRecognizerHelper(context)
        return GestureAnalysisAnalyzer(gestureRecognizer)
    }

    private fun determineScreenConfiguration(
        currentConfiguration: Configuration
    ): Int {
        val targetRotation = if (currentConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Surface.ROTATION_90
        } else {
            Surface.ROTATION_0
        }
        return targetRotation
    }
}