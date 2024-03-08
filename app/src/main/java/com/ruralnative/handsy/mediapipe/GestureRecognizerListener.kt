package com.ruralnative.handsy.mediapipe

interface GestureRecognizerListener {
    fun onError(error: String, errorCode: Int = GestureRecognizerConstants.OTHER_ERROR)
    fun onResults(resultBundle: ResultBundle)
}