package com.ruralnative.handsy.ui.lesson_list_screen

data class LessonListState(
    val alphabetLessons: List<LessonCardState>,
    val phrasesLesson: List<LessonCardState>
)