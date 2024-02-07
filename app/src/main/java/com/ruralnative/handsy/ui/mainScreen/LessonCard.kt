package com.ruralnative.handsy.ui.mainScreen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LessonCard(
    val lessonID: Int,
    val lessonName: String,
    @StringRes val lessonDescription: Int,
    @DrawableRes val lessonMediaResource: Int
)
