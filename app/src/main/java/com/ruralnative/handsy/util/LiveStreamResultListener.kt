package com.ruralnative.handsy.util

import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.core.OutputHandler
import com.google.mediapipe.tasks.vision.gesturerecognizer.GestureRecognizerResult

interface LiveStreamResultListener: OutputHandler.ResultListener<GestureRecognizerResult, MPImage> {

    @Override
    override fun run(result: GestureRecognizerResult?, input: MPImage?) {
        TODO("Not yet implemented")
    }
}