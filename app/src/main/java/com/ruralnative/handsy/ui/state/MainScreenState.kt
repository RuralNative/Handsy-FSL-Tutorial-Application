package com.ruralnative.handsy.ui.state

data class MainScreenState(
    val alphabetLessons: List<LessonCardState>,
    val phrasesLesson: List<LessonCardState>
)