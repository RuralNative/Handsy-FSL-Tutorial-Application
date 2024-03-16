package com.ruralnative.handsy.util.ai.mediapipe

object GestureRecognizerConstants {
    val TAG = "GestureRecognizerHelper ${this.hashCode()}"
    const val MP_RECOGNIZER_TASK = "gesture_recognizer.task"

    const val DELEGATE_CPU = 0
    const val DELEGATE_GPU = 1
    const val DEFAULT_HAND_DETECTION_CONFIDENCE = 0.5F
    const val DEFAULT_HAND_TRACKING_CONFIDENCE = 0.5F
    const val DEFAULT_HAND_PRESENCE_CONFIDENCE = 0.5F
    const val OTHER_ERROR = 0
    const val GPU_ERROR = 1
}