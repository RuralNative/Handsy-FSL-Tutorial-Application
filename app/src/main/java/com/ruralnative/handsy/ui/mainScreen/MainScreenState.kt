package com.ruralnative.handsy.ui.mainScreen

data class MainScreenState(
    val alphabetLessons: List<LessonCardState>? = null,
    val phrasesLesson: List<LessonCardState>? = null,
)