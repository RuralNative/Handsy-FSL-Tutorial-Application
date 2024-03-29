package com.ruralnative.handsy.util

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