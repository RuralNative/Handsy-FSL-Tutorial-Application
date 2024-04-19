package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition

import com.google.mediapipe.tasks.components.containers.Category
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

data class CameraGestureRecognitionState(
    val resultName: String = "-",
    val resultScore: String = "0.00",
    val inferenceTime: Long = 0,
    val inputImageHeight: Int = 0,
    val inputImageWidth: Int = 0
)

