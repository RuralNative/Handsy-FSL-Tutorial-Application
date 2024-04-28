package com.ruralnative.handsy.ui.dev_intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.ui.user_intro.UserIntroState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * DevsIntroViewModel manages the logic for the [DevsIntroScreen].
 * It handles navigation to the main screen.
 */
@HiltViewModel
class DevsIntroViewModel @Inject constructor(
): ViewModel() {

    private val _uiState = MutableStateFlow(DevsIntroState())
    val uiState: StateFlow<DevsIntroState> = _uiState.asStateFlow()

    /**
     * Navigates to the HomeScreen after a specified delay.
     *
     * @param onNavigateToMain A lambda function to be called after the delay, which performs the navigation.
     */
    fun navigateToHome(
        onNavigateToMain: () -> Unit
    ) {
        viewModelScope.launch {
            delay(300)
            onNavigateToMain()
        }
    }

    fun setHeaderVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(headerVisibility = isVisible)
        }
    }

    fun setImageVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(imageVisibility = isVisible)
        }
    }

    fun setButtonVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(buttonVisibility = isVisible)
        }
    }
}