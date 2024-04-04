package com.ruralnative.handsy.ui.main_screen

import com.ruralnative.handsy.ui.main_screen.LessonCardState

data class MainScreenState(
    val alphabetLessons: List<LessonCardState>,
    val phrasesLesson: List<LessonCardState>
)