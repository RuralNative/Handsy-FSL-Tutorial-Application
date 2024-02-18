package com.ruralnative.handsy.ui.lessonScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: AlphabetLessonRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LessonState())
    val uiState: StateFlow<LessonState> = _uiState.asStateFlow()
}