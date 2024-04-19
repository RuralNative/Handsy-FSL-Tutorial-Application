package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition

import android.content.Context
import android.content.res.Configuration
import android.util.Log
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
import com.google.mediapipe.tasks.components.containers.Category
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai.GestureAnalysisAnalyzer
import com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai.GestureRecognizerHelper
import com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai.GestureRecognizerListener
import com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai.ResultBundle
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

    private val _uiState = MutableStateFlow(CameraGestureRecognitionState())
    val uiState: StateFlow<CameraGestureRecognitionState> = _uiState.asStateFlow()

    private val backgroundExecutor = Executors.newSingleThreadExecutor()

    fun setupCameraProvider(
        context: Context,
        currentConfiguration: Configuration,
        currentLifecycleOwner: LifecycleOwner,
        cameraView: PreviewView
    ) {
        viewModelScope.launch {
            val gestureAnalyzer = setupGestureRecognizerAnalyzer(context)
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            cameraProviderFuture.addListener(
                {
                    val cameraProvider = cameraProviderFuture.get()

                    val preview =
                        Preview.Builder()
                            .setTargetRotation(determineScreenConfiguration(currentConfiguration))
                            .build()

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

                    cameraProvider.unbindAll()

                    cameraProvider.bindToLifecycle(
                        currentLifecycleOwner, cameraSelector, preview, imageAnalysis
                    )

                    preview.setSurfaceProvider(
                        cameraView.surfaceProvider
                    )
                },
                ContextCompat.getMainExecutor(context)
            )
        }
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

    private fun setupGestureRecognizerAnalyzer(
        context: Context
    ): GestureAnalysisAnalyzer {
        val gestureRecognizer = GestureRecognizerHelper(context, this)
        return GestureAnalysisAnalyzer(gestureRecognizer)
    }

    private fun extractGesturesList(list: List<GestureRecognizerResult>): List<String> {
        val extractedCategoryList: List<Category>

        val rawGesturesList = list.first().gestures()
        extractedCategoryList = if (rawGesturesList.isNotEmpty()) {
            rawGesturesList.first().sortedByDescending { it.score() }
        } else {
            emptyList()
        }

        val categoryResult: String = if (extractedCategoryList.isNotEmpty()) {
            extractedCategoryList.first().categoryName()
        } else {
            "-"
        }

        val categoryScore: String = if (extractedCategoryList.isNotEmpty()) {
            extractedCategoryList.first().score().toString()
        } else {
            "0.00"
        }
        return listOf(categoryResult, categoryResult)
    }

    fun onError(error: String, errorCode: Int = GestureRecognizerHelper.OTHER_ERROR) {
        print(error)
        print(errorCode.toString())
    }

    fun onResults(resultBundle: ResultBundle) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                resultName = extractGesturesList(resultBundle.results).first(),
                resultScore = extractGesturesList(resultBundle.results).last(),
                inferenceTime = resultBundle.inferenceTime,
                inputImageHeight = resultBundle.inputImageHeight,
                inputImageWidth = resultBundle.inputImageWidth
            )
            Log.d("AI_Gesture", "onResult()")
            Log.d("AI_Gesture", "Result: + ${extractGesturesList(resultBundle.results).first()}")
            Log.d("AI_Gesture", "Score: + ${extractGesturesList(resultBundle.results).last()}")
            Log.d("AI_Gesture", "Inference Time: + ${resultBundle.inferenceTime}")
            Log.d("AI_Gesture", "Input ImageWidth: + ${resultBundle.inputImageWidth}")
            Log.d("AI_Gesture", "Input ImageHeight : + ${resultBundle.inputImageHeight}")
        }
    }
}