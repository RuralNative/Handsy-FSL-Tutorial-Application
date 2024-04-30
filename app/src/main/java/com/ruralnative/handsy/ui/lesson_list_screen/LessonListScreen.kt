package com.ruralnative.handsy.ui.lesson_list_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun LessonListScreen(
    modifier: Modifier,
    viewModel: LessonListViewModel = hiltViewModel(),
    navigateToLessonScreen: (id: Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lessons = uiState.alphabetLessons
    val firstListItem = uiState.firstVisibleListItem
    val updateFirstListItem: (Int) -> Unit = { viewModel.setFirstItemIndex(it) }

    Surface(modifier = modifier) {
        LessonCardList(
            modifier = Modifier
                .fillMaxSize(),
            firstListItem,
            lessonHeader = "Know Your Alphabet",
            lessonList = lessons,
            updateFirstListItem,
            navigateToLessonScreen,
        )
    }
}