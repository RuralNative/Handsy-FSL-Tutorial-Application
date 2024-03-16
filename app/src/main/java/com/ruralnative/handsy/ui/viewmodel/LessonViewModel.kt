package com.ruralnative.handsy.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.ui.state.LessonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LessonViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: AlphabetLessonRepository
): ViewModel() {

    val lessonID = savedStateHandle.get<Int>("lessonID")!!

    private val _uiState = MutableStateFlow(LessonState())
    val uiState: StateFlow<LessonState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getLessonByID(lessonID).collect {lesson ->
                _uiState.value = _uiState.value.copy(
                    lessonName = lesson.lessonName,
                    lessonDescription = lesson.lessonDescription,
                    lessonMediaResource = lesson.lessonMediaFile
                )
            }
        }
    }
}