package com.ruralnative.handsy.ui.state

import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.ruralnative.handsy.util.GestureRecognizerHelper
import com.ruralnative.handsy.util.ResultBundle

data class CameraLiveStreamState (
    val results: List<GestureRecognizerResult>,
    val inferenceTime: Long = 0,
    val inputImageHeight: Int = 0,
    val inputImageWidth: Int = 0,
)