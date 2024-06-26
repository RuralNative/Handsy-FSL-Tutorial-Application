package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.ai

/*
 * Copyright 2022 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.SystemClock
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.camera.core.ImageProxy
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.core.Delegate
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizer
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult
import com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition.CameraGestureRecognitionViewModel

class GestureRecognizerHelper(
    val context: Context,
    viewModel: CameraGestureRecognitionViewModel
) {

    /**
     * Companion object containing constants and utility methods for the GestureRecognizerHelper class.
     *
     * This object defines constants for delegate types, default confidence thresholds, error codes, and the tag used for logging.
     * It also initializes the `TAG` with a unique identifier based on the hash code of the companion object itself.
     */
    companion object {
        val TAG = "GestureRecognizerHelper ${this.hashCode()}"
        private const val MP_RECOGNIZER_TASK = "gesture_recognizer.task"

        const val DELEGATE_CPU = 0
        const val DELEGATE_GPU = 1
        const val DEFAULT_HAND_DETECTION_CONFIDENCE = 0.5F
        const val DEFAULT_HAND_TRACKING_CONFIDENCE = 0.5F
        const val DEFAULT_HAND_PRESENCE_CONFIDENCE = 0.5F
        const val OTHER_ERROR = 0
        const val GPU_ERROR = 1
    }

    private var minHandDetectionConfidence: Float = DEFAULT_HAND_DETECTION_CONFIDENCE
    private var minHandTrackingConfidence: Float = DEFAULT_HAND_TRACKING_CONFIDENCE
    private var minHandPresenceConfidence: Float = DEFAULT_HAND_PRESENCE_CONFIDENCE
    private val gestureRecognizerListener: CameraGestureRecognitionViewModel = viewModel
    private var currentDelegate: Int = DELEGATE_CPU
    private val currentRunningMode: RunningMode = RunningMode.LIVE_STREAM
    private var gestureRecognizer: GestureRecognizer? = null

    init {
        setupGestureRecognizer()
    }

    fun clearGestureRecognizer() {
        gestureRecognizer?.close()
        gestureRecognizer = null
    }

    /**
     * Initializes the gesture recognizer with the current settings.
     *
     * This method configures the gesture recognizer to use either CPU or GPU for computation,
     * sets the model asset path, and applies various options such as confidence thresholds and running mode.
     * The GPU delegate requires the recognizer to be initialized on the same thread it will be used on.
     *
     * @throws IllegalStateException if the gesture recognizer fails to initialize due to an illegal state.
     * @throws RuntimeException if the gesture recognizer fails to initialize due to a runtime error,
     * especially relevant when using the GPU delegate.
     */
    private fun setupGestureRecognizer() {
        val baseOptionBuilder = BaseOptions.builder()
        when (currentDelegate) {
            DELEGATE_CPU -> {
                baseOptionBuilder.setDelegate(Delegate.CPU)
            }
            DELEGATE_GPU -> {
                baseOptionBuilder.setDelegate(Delegate.GPU)
            }
        }
        baseOptionBuilder.setModelAssetPath(MP_RECOGNIZER_TASK)
        try {
            val baseOptions = baseOptionBuilder.build()
            val optionsBuilder =
                GestureRecognizer.GestureRecognizerOptions.builder()
                    .setBaseOptions(baseOptions)
                    .setMinHandDetectionConfidence(minHandDetectionConfidence)
                    .setMinTrackingConfidence(minHandTrackingConfidence)
                    .setMinHandPresenceConfidence(minHandPresenceConfidence)
                    .setRunningMode(currentRunningMode)

            if (currentRunningMode == RunningMode.LIVE_STREAM) {
                optionsBuilder
                    .setResultListener(this::returnLivestreamResult)
                    .setErrorListener(this::returnLivestreamError)
            }
            val options = optionsBuilder.build()
            gestureRecognizer =
                GestureRecognizer.createFromOptions(context, options)
        } catch (e: IllegalStateException) {
            gestureRecognizerListener.onError(
                "Gesture recognizer failed to initialize. See error logs for " + "details"
            )
            Log.e(
                TAG,
                "MP Task Vision failed to load the task with error: " + e.message
            )
        } catch (e: RuntimeException) {
            gestureRecognizerListener.onError(
                "Gesture recognizer failed to initialize. See error logs for " + "details",
                GPU_ERROR
            )
            Log.e(
                TAG,
                "MP Task Vision failed to load the task with error: " + e.message
            )
        }
    }

    /**
     * Converts an `ImageProxy` to an `MPImage` and feeds it to the GestureRecognizer for live stream gesture recognition.
     *
     * This function processes a frame from the camera, converts it into a bitmap, applies necessary transformations
     * such as rotation and flipping for front camera usage, and then converts it into an `MPImage` object. This object
     * is then passed to the gesture recognizer for gesture analysis.
     *
     * @param imageProxy The `ImageProxy` object representing a frame from the camera.
     * @throws IllegalStateException if the `ImageProxy` is already closed or if the bitmap conversion fails.
     */
    fun recognizeLiveStream(
        imageProxy: ImageProxy,
    ) {
        val frameTime = SystemClock.uptimeMillis()
        // Copy out RGB bits from the frame to a bitmap buffer
        val bitmapBuffer = Bitmap.createBitmap(
            imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888
        )
        imageProxy.use { bitmapBuffer.copyPixelsFromBuffer(imageProxy.planes[0].buffer) }
        imageProxy.close()
        val matrix = Matrix().apply {
            // Rotate the frame received from the camera to be in the same direction as it'll be shown
            postRotate(imageProxy.imageInfo.rotationDegrees.toFloat())
            // flip image since we only support front camera
            postScale(
                -1f, 1f, imageProxy.width.toFloat(), imageProxy.height.toFloat()
            )
        }
        // Rotate bitmap to match what our model expects
        val rotatedBitmap = Bitmap.createBitmap(
            bitmapBuffer,
            0,
            0,
            bitmapBuffer.width,
            bitmapBuffer.height,
            matrix,
            true
        )
        // Convert the input Bitmap object to an MPImage object to run inference
        val mpImage = BitmapImageBuilder(rotatedBitmap).build()
        recognizeAsync(mpImage, frameTime)
    }

    /**
     * Asynchronously runs hand gesture recognition using the MediaPipe Gesture Recognition API.
     *
     * This method is designed for use in live stream mode, where the recognition result will be returned
     * via the `returnLivestreamResult` function. It takes an `MPImage` object and a frame time as input,
     * and initiates the gesture recognition process asynchronously.
     *
     * @param mpImage The `MPImage` object containing the image data to be analyzed.
     * @param frameTime The timestamp of the frame being analyzed, in milliseconds.
     * @throws IllegalStateException if the gesture recognizer is not properly initialized or if the
     * recognition process encounters an illegal state.
     */
    @VisibleForTesting
    fun recognizeAsync(mpImage: MPImage, frameTime: Long) {
        // As we're using running mode LIVE_STREAM, the recognition result will
        // be returned in returnLivestreamResult function
        gestureRecognizer?.recognizeAsync(mpImage, frameTime)
    }

    /**
     * Checks if the gesture recognizer helper is closed.
     *
     * This function returns `true` if the gesture recognizer has been closed, indicating that it is no longer available for use.
     * It is useful for determining the current state of the gesture recognizer and ensuring that operations are not attempted on a closed recognizer.
     *
     * @return `true` if the gesture recognizer is closed, `false` otherwise.
     */
    fun isClosed(): Boolean {
        return gestureRecognizer == null
    }

    /**
     * Returns the recognition result to the caller of the GestureRecognizerHelper.
     *
     * This private function is used to deliver the results of gesture recognition from a live stream to the listener
     * set in the GestureRecognizerHelper. It calculates the inference time and constructs a `ResultBundle` with
     * the recognition result, inference time, and dimensions of the input image. This bundle is then passed to the
     * listener's `onResults` method.
     *
     * @param result The `GestureRecognizerResult` containing the gesture recognition result.
     * @param input The `MPImage` object representing the input image for which the recognition was performed.
     */
    private fun returnLivestreamResult(
        result: GestureRecognizerResult, input: MPImage
    ) {
        val finishTimeMs = SystemClock.uptimeMillis()
        val inferenceTime = finishTimeMs - result.timestampMs()
        gestureRecognizerListener.onResults(
            ResultBundle(
                listOf(result), inferenceTime, input.height, input.width
            )
        )
    }

    private fun returnLivestreamError(error: RuntimeException) {
        gestureRecognizerListener.onError(
            error.message ?: "An unknown error has occurred"
        )
    }
}