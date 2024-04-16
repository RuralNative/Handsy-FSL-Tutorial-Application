package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai

import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

/**
 * A data class encapsulating the results of gesture recognition.
 *
 * This class contains the list of `GestureRecognizerResult` objects, the inference time, and the dimensions of the input image.
 * It is used to deliver the results of gesture recognition to the caller of the GestureRecognizerHelper.
 *
 * @property results A list of `GestureRecognizerResult` objects representing the recognized gestures.
 * @property inferenceTime The time taken for inference, in milliseconds.
 * @property inputImageHeight The height of the input image, in pixels.
 * @property inputImageWidth The width of the input image, in pixels.
 */
data class ResultBundle(
    val results: List<GestureRecognizerResult>,
    val inferenceTime: Long,
    val inputImageHeight: Int,
    val inputImageWidth: Int
)