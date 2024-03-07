package com.ruralnative.handsy.ui.cameraScreen

data class CameraState (
    val delegate: Int = GestureRecognizerHelper.DELEGATE_CPU,
    val minimumHandDetectionConfidence: Float = GestureRecognizerHelper.DEFAULT_HAND_DETECTION_CONFIDENCE,
    val minimumHandTrackingConfidence: Float = GestureRecognizerHelper.DEFAULT_HAND_TRACKING_CONFIDENCE,
    val minimumHandPresenceConfidence: Float = GestureRecognizerHelper.DEFAULT_HAND_PRESENCE_CONFIDENCE
)