package com.ruralnative.handsy.ui.mainScreen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LessonCardState(
    val lessonID: Int,
    val lessonName: String,
    @DrawableRes val lessonMediaResource: Int
)
