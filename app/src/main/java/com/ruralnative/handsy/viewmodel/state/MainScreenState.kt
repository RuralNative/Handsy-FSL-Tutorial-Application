package com.ruralnative.handsy.viewmodel.state

data class MainScreenState(
    val alphabetLessons: List<LessonCardState>,
    val phrasesLesson: List<LessonCardState>
)