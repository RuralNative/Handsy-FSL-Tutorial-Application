package com.ruralnative.handsy.util

import android.graphics.Bitmap
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