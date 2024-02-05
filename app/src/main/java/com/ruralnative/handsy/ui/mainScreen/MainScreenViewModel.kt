package com.ruralnative.handsy.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.data.repository.PhrasesLessonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val alphabetRepository: AlphabetLessonRepository,
    private val phrasesRepository: PhrasesLessonRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState: StateFlow<MainScreenState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            alphabetRepository.allLessons.collect() {lessons ->
                _uiState.value = _uiState.value.copy(alphabetLessons = lessons)
            }
        }
        viewModelScope.launch {
            phrasesRepository.allLessons.collect() {lessons ->
                _uiState.value = _uiState.value.copy(phrasesLesson = lessons)
            }
        }
    }
}