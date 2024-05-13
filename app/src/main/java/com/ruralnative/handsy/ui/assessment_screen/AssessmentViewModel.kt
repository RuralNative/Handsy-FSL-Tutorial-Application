package com.ruralnative.handsy.ui.assessment_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssessmentViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(AssessmentState())
    val uiState = _uiState.asStateFlow()

    init {
        setInitialScreenContent()
    }

    private fun setInitialScreenContent(
    ) {
        viewModelScope.launch {
            // Determine value for State change
            val newDisplayedMediaResource: String =
                _uiState.value.questionMedia[_uiState.value.questionNumber]
            val newDisplayedMediaDescription: String =
                _uiState.value.questionDescription[_uiState.value.questionNumber]
            val newDisplayedMediaAnswer: String =
                _uiState.value.questionAnswer[_uiState.value.questionNumber]

            // Change State based from values
            _uiState.value = _uiState.value.copy(
                displayedMedia = newDisplayedMediaResource,
                displayedDescription = newDisplayedMediaDescription,
                displayedAnswer = newDisplayedMediaAnswer
            )
        }
    }

    private fun setScreenContent(
        onNavigateToHome: () -> Unit
    ) {
        viewModelScope.launch {
            //Preliminary Check
            val currentQuestionNumber: Int = _uiState.value.questionNumber

            //Action based on QuestionNumber
            if (currentQuestionNumber < 3) {
                // Determine value for State change
                val newDisplayedMediaResource: String =
                    _uiState.value.questionMedia[_uiState.value.questionNumber]
                val newDisplayedMediaDescription: String =
                    _uiState.value.questionDescription[_uiState.value.questionNumber]
                val newDisplayedMediaAnswer: String =
                    _uiState.value.questionAnswer[_uiState.value.questionNumber]

                // Change State based from values
                _uiState.value = _uiState.value.copy(
                    displayedMedia = newDisplayedMediaResource,
                    displayedDescription = newDisplayedMediaDescription,
                    displayedAnswer = newDisplayedMediaAnswer
                )
            } else {
                onNavigateToHome()
            }
        }
    }

    fun onIncorrectButtonClicked(
        onNavigateToHome: () -> Unit
    ) {
        viewModelScope.launch {
           //New Values
            val score: Int = _uiState.value.numberOfCorrectAnswers
            val newQuestionNumber: Int = _uiState.value.questionNumber + 1
            //Update Result
            _uiState.value = _uiState.value.copy(
                questionNumber = newQuestionNumber,
                questionResultDescription = "WRONG ANSWER"
            )
            delay(500)
            setScreenContent { onNavigateToHome() }
            _uiState.value = _uiState.value.copy(
                questionResultDescription = "Score: $score / 3"
            )
        }
    }

    fun onCorrectButtonClicked(
        onNavigateToHome: () -> Unit
    ) {
        viewModelScope.launch {
            // Add 1 to Score
            val newScore: Int = _uiState.value.numberOfCorrectAnswers + 1
            val newQuestionNumber: Int = _uiState.value.questionNumber + 1
            _uiState.value = _uiState.value.copy(
                numberOfCorrectAnswers = newScore
            )
            // Update Result
            _uiState.value = _uiState.value.copy(
                questionNumber = newQuestionNumber,
                questionResultDescription = "CORRECT ANSWER"
            )
            delay(500)
            setScreenContent { onNavigateToHome() }
            _uiState.value = _uiState.value.copy(
                questionResultDescription = "Score: $newScore / 3"
            )
        }
    }

}