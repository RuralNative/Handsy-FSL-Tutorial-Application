package com.ruralnative.handsy.util

import android.content.Context
import androidx.camera.core.ImageProxy

class GestureRecognizerClassifier(context: Context) : GestureRecognizerListener {

    private val gestureRecognizerHelper: GestureRecognizerHelper = GestureRecognizerHelper(context)

    private fun recognizeHand(imageProxy: ImageProxy) {
        gestureRecognizerHelper.recognizeLiveStream(
            imageProxy = imageProxy,
        )
    }

    override fun onResults(
        resultBundle: ResultBundle
    ) {
        val gestureResults = resultBundle.results.first().gestures()
        if (gestureResults.isNotEmpty()) {
            TODO("Update UI with gestureResults.first().name")
        } else {
            TODO("Update UI with Empty List")
        }

        TODO("Draw dynamically Gesture Results on Canvas")
    }

    override fun onError(error: String, errorCode: Int) {
        TODO("Update UI with Error")
    }
}