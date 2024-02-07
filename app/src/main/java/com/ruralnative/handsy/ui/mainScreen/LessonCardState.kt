package com.ruralnative.handsy.ui.mainScreen

import androidx.annotation.DrawableRes

data class LessonCardState(
    val lessonID: Int,
    val lessonName: String?,
    @DrawableRes val lessonMediaResource: String?
)
