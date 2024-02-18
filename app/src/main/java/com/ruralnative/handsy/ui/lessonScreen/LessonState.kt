package com.ruralnative.handsy.ui.lessonScreen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LessonState(
    val lessonName: String = "Name",
    val lessonDescription: String = "Description",
    val lessonMediaResource: String = "Resource"
)
