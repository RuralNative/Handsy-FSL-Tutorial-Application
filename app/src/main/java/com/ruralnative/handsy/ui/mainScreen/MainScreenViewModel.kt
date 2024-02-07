package com.ruralnative.handsy.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.data.repository.PhrasesLessonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val alphabetRepository: AlphabetLessonRepository,
    private val phrasesRepository: PhrasesLessonRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenState())
    val uiState: StateFlow<MainScreenState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            phrasesRepository.allLessons.collect() {lessons ->
                _uiState.value = _uiState.value.copy(phrasesLesson = setPhraseLessonCards(lessons))
            }
        }
    }

    private suspend fun setPhraseLessonCards(lessons: List<PhrasesLesson>): MutableList<LessonCardState> {
        val phraseLessons: MutableList<LessonCardState> = mutableListOf()
        for (lesson: PhrasesLesson in lessons) {
            val lessonCard: LessonCardState
            val lessonID = lesson.id
            val lessonName = lesson.lessonName
            val lessonMediaSource = lesson.lessonMediaFile
            lessonCard = LessonCardState(lessonID, lessonName,
                lessonMediaSource?.toInt()
            )
            phraseLessons.add(lessonCard)
        }
        return phraseLessons
    }
}