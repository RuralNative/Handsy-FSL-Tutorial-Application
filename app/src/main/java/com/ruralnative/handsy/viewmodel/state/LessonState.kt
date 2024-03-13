package com.ruralnative.handsy.viewmodel.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class LessonState(
    val lessonName: String = "A",
    val lessonDescription: String = "alphabet_a_description",
    val lessonMediaResource: String = "letter_a"
)
