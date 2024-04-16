package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai

import android.content.Context
import androidx.camera.core.ImageProxy
import java.util.concurrent.Executor

class GestureRecognizerClassifier(context: Context) :
    GestureRecognizerListener
{

    private lateinit var backgroundExecutor: Executor


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