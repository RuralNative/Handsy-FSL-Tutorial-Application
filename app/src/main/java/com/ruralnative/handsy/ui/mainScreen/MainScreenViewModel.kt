package com.ruralnative.handsy.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.entities.AlphabetLesson
import com.ruralnative.handsy.data.entities.PhrasesLesson
import com.ruralnative.handsy.data.repository.AlphabetLessonRepository
import com.ruralnative.handsy.data.repository.PhrasesLessonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel (
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
            alphabetRepository.allLessons.collect() {lessons ->
                _uiState.value = _uiState.value.copy(alphabetLessons = setAlphabetLessonCards(lessons))
            }
        }
    }

    private fun setPhraseLessonCards(lessons: List<PhrasesLesson>): MutableList<LessonCardState> {
        val lessonsList: MutableList<LessonCardState> = mutableListOf()
        for (lesson: PhrasesLesson in lessons) {
            val lessonCard: LessonCardState
            val lessonID: Int = lesson.id
            val lessonName: String? = lesson.lessonName
            val lessonResource: String? = lesson.lessonMediaFile
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
            val lessonName: String? = lesson.lessonName
            val lessonResource: String? = lesson.lessonMediaFile
            lessonCard = LessonCardState(lessonID, lessonName,
                lessonResource
            )
            lessonsList.add(lessonCard)
        }
        return lessonsList
    }
}