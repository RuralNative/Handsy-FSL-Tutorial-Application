package com.ruralnative.handsy.ui.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.data.repository.PhrasesLessonRepository
import com.ruralnative.handsy.ui.state.LessonCardState
import com.ruralnative.handsy.ui.state.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val alphabetRepository: AlphabetLessonRepository,
    private val phrasesRepository: PhrasesLessonRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(
        MainScreenState(
        alphabetLessons = emptyList(),
        phrasesLesson = emptyList()
    )
    )
    val uiState: StateFlow<MainScreenState> = _uiState.asStateFlow()

    init {
        Log.d("LessonList", "ViewModel INITIALIZED")
        viewModelScope.launch {
            alphabetRepository.allLessons.collect {lessons ->
                _uiState.value = _uiState.value.copy(alphabetLessons = setAlphabetLessonCards(lessons))
                Log.d("LessonList", "alphabetLessons = ${lessons.size}")
            }
            phrasesRepository.allLessons.collect {lessons ->
                _uiState.value = _uiState.value.copy(phrasesLesson = setPhraseLessonCards(lessons))
                Log.d("LessonList", "phrasesLessons = ${lessons.size}")
            }
        }
    }

    private fun setPhraseLessonCards(lessons: List<PhrasesLesson>): MutableList<LessonCardState> {
        val lessonsList: MutableList<LessonCardState> = mutableListOf()
        for (lesson: PhrasesLesson in lessons) {
            val lessonCard: LessonCardState
            val lessonID: Int = lesson.id
            val lessonName: String = lesson.lessonName
            val lessonResource: String = lesson.lessonMediaFile
            lessonCard = LessonCardState(lessonID, lessonName,
                lessonResource
            )
            lessonsList.add(lessonCard)
        }
        return lessonsList
    }

    private fun setAlphabetLessonCards(lessons: List<AlphabetLesson>): MutableList<LessonCardState> {
        val lessonsList: MutableList<LessonCardState> = mutableListOf()
        for (lesson: AlphabetLesson in lessons) {
            val lessonCard: LessonCardState
            val lessonID: Int = lesson.id
            val lessonName: String = lesson.lessonName
            val lessonResource: String = lesson.lessonMediaFile
            lessonCard = LessonCardState(lessonID, lessonName,
                lessonResource
            )
            lessonsList.add(lessonCard)
        }
        return lessonsList
    }
}