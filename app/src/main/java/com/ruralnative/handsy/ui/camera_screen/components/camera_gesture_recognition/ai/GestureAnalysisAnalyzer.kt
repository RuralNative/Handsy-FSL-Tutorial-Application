package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class GestureAnalysisAnalyzer (
    private val gestureRecognizerHelper: GestureRecognizerHelper,
    private val outputImageFormat: Int,
    private val backpressureStrategy: Int
) : ImageAnalysis.Analyzer {

    override fun analyze(imageProxy: ImageProxy) {
        gestureRecognizerHelper.recognizeLiveStream(imageProxy)
        imageProxy.close()
    }
}