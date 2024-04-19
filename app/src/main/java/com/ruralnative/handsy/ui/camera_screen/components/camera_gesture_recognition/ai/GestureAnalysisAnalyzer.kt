package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class GestureAnalysisAnalyzer (
    private val gestureRecognizerHelper: GestureRecognizerHelper
) : ImageAnalysis.Analyzer {

    override fun analyze(imageProxy: ImageProxy) {
        gestureRecognizerHelper.recognizeLiveStream(imageProxy)
        imageProxy.close()
    }
}