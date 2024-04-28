package com.ruralnative.handsy.ui.user_intro

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ruralnative.handsy.data.entities.User
import com.ruralnative.handsy.data.repository.UserRepository
import com.ruralnative.handsy.ui.user_intro.UserIntroState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UserIntroViewModel manages the state and logic for the [UserIntroScreen].
 */
@HiltViewModel
class UserIntroViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UserIntroState())
    val uiState: StateFlow<UserIntroState> = _uiState.asStateFlow()

    /**
     * Updates the user name state in the UI state.
     *
     * @param newNameState The new user name state to update with.
     */
    fun updateUserNameState(newNameState: String) {
        _uiState.value = _uiState.value.copy(userNameState = newNameState)
    }

    /**
     * Saves the user name to the database.
     *
     * @param nameInput The user name input to save.
     */
    fun saveUserNameInDatabase(
        nameInput: String,
        onNavigateToDevsIntro: () -> Unit
        ) {
        viewModelScope.launch {
            repository.insertUser(
                User(
                    id = 1,
                    userName = nameInput,
                    isNewUser = 0,
                    progressionLevel = 1
                )
            )
            setHeaderVisibilty(false)
            setImageVisibility(false)
            setTextFieldVisibility(false)
            delay(500)
            onNavigateToDevsIntro()
        }
    }

    fun setHeaderVisibilty(isVisible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(headerVisibility = isVisible)
        }
    }

    fun setImageVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(imageVisibility = isVisible)
        }
    }

    fun setTextFieldVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(textFieldVisibility = isVisible)
        }
    }
}