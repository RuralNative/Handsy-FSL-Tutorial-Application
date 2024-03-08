package com.ruralnative.handsy.ai

import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

data class ResultBundle(
    val results: List<GestureRecognizerResult>,
    val inferenceTime: Long,
    val inputImageHeight: Int,
    val inputImageWidth: Int,
)
