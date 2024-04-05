package com.ruralnative.handsy.ui.main_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ruralnative.handsy.ui.components.LessonCardList

@Composable
fun LessonListScreen(
    viewModel: LessonListViewModel = hiltViewModel(),
    navigateToLessonScreen: (id: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lessons = uiState.alphabetLessons

    LessonCardList(
        modifier = Modifier
            .fillMaxSize(),
        lessonHeader = "Know Your Alphabet",
        lessonList = lessons,
        navigateToLessonScreen
    )
}