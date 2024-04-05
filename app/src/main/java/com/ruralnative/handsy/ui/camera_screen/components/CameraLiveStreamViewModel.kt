package com.ruralnative.handsy.ui.camera_screen.components

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.util.GestureAnalysisAnalyzer
import com.ruralnative.handsy.util.GestureRecognizerHelper
import com.ruralnative.handsy.util.GestureRecognizerListener
import com.ruralnative.handsy.util.ResultBundle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraLiveStreamViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel(), GestureRecognizerListener {

    private val _uiState = MutableStateFlow(CameraLiveStreamState(results = emptyList()))
    val uiState: StateFlow<CameraLiveStreamState> = _uiState.asStateFlow()

    override fun onError(error: String, errorCode: Int) {
        TODO("Update UI to Show Error")
    }

    override fun onResults(resultBundle: ResultBundle) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                results = resultBundle.results,
                inferenceTime = resultBundle.inferenceTime,
                inputImageHeight = resultBundle.inputImageHeight,
                inputImageWidth = resultBundle.inputImageWidth
            )
        }
    }

    fun initializeAnalyzer(context: Context): GestureAnalysisAnalyzer {
        return GestureAnalysisAnalyzer(
            GestureRecognizerHelper(context),
            ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888,
            ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
        )
    }

    fun initializeCameraController(
        context: Context,
        gestureAnalyzer: GestureAnalysisAnalyzer
    ): LifecycleCameraController {
        val controller = LifecycleCameraController(context).apply {
            setEnabledUseCases(CameraController.IMAGE_ANALYSIS)
            setImageAnalysisAnalyzer(
                ContextCompat.getMainExecutor(context),
                gestureAnalyzer
            )
        }
        controller.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
        return controller
    }
}