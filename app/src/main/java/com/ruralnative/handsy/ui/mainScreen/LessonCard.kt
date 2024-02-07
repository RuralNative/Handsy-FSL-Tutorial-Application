package com.ruralnative.handsy.ui.mainScreen

import androidx.annotation.DrawableRes

data class LessonCard(
    val lessonID: Int,
    val lessonName: String,
    @DrawableRes val lessonMediaResource: String
)
