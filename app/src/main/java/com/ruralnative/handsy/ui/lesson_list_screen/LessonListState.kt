package com.ruralnative.handsy.ui.lesson_list_screen

import androidx.compose.foundation.lazy.LazyListState

data class LessonListState(
    val listState: LazyListState? = null,
    val firstVisibleListItem: Int = 1,
    val alphabetLessons: List<LessonCardState>,
    val phrasesLesson: List<LessonCardState>
)