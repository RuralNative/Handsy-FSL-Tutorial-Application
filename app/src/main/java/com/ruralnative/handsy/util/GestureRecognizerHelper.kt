package com.ruralnative.handsy.util

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
import android.media.MediaMetadataRetriever
import android.net.Uri
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

class GestureRecognizerHelper(
    val context: Context,
    val runningMode: RunningMode
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
    private val gestureRecognizerListener: GestureRecognizerListener? = null
    private var currentDelegate: Int = DELEGATE_CPU
    private val currentRunningMode: RunningMode = runningMode
    // For this example this needs to be a var so it can be reset on changes. If the GestureRecognizer
    // will not change, a lazy val would be preferable.
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
            gestureRecognizerListener?.onError(
                "Gesture recognizer failed to initialize. See error logs for " + "details"
            )
            Log.e(
                TAG,
                "MP Task Vision failed to load the task with error: " + e.message
            )
        } catch (e: RuntimeException) {
            gestureRecognizerListener?.onError(
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
     * Runs gesture recognition on a single image and returns the results.
     *
     * This function accepts a `Bitmap` object representing an image, converts it to an `MPImage` object, and then runs the gesture recognizer on it. The recognition results are encapsulated in a `ResultBundle` and returned to the caller.
     *
     * @param image The `Bitmap` object containing the image data to be analyzed.
     * @return A `ResultBundle` containing the recognition results, or `null` if an error occurs during the recognition process.
     * @throws IllegalArgumentException if the running mode is not set to `RunningMode.IMAGE`.
     */
    fun recognizeImage(image: Bitmap): ResultBundle? {
        if (currentRunningMode != RunningMode.IMAGE) {
            throw IllegalArgumentException(
                "Attempting to call detectImage" +
                        " while not using RunningMode.IMAGE"
            )
        }
        // Inference time is the difference between the system time at the
        // start and finish of the process
        val startTime = SystemClock.uptimeMillis()
        // Convert the input Bitmap object to an MPImage object to run inference
        val mpImage = BitmapImageBuilder(image).build()
        // Run gesture recognizer using MediaPipe Gesture Recognizer API
        gestureRecognizer?.recognize(mpImage)?.also { recognizerResult ->
            val inferenceTimeMs = SystemClock.uptimeMillis() - startTime
            return ResultBundle(
                listOf(recognizerResult),
                inferenceTimeMs,
                image.height,
                image.width
            )
        }
        // If gestureRecognizer?.recognize() returns null, this is likely an error. Returning null
        // to indicate this.
        gestureRecognizerListener?.onError(
            "Gesture Recognizer failed to recognize."
        )
        return null
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
    fun returnLivestreamResult(
        result: GestureRecognizerResult, input: MPImage
    ) : ResultBundle {
        val finishTimeMs = SystemClock.uptimeMillis()
        val inferenceTime = finishTimeMs - result.timestampMs()

        return ResultBundle(
            listOf(result), inferenceTime, input.height, input.width
        )
    }

    private fun returnLivestreamError(error: RuntimeException): RuntimeException {
        return error
    }

    /**
     * Interface for listening to gesture recognition results and errors.
     *
     * Implement this interface to receive gesture recognition results and errors from the GestureRecognizerHelper.
     * The `onResults` method is called with a `ResultBundle` containing the recognition results, inference time, and input image dimensions.
     * The `onError` method is called when an error occurs during the recognition process, providing an error message and an optional error code.
     */
    interface GestureRecognizerListener {
        fun onError(error: String, errorCode: Int = GestureRecognizerHelper.OTHER_ERROR)
        fun onResults(resultBundle: ResultBundle)
    }
}