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
 * It interacts with the [UserRepository] to perform operations related to user data.
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
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(userNameState = newNameState)
            if (newNameState.length <= 10) {
                _uiState.value = _uiState.value.copy(isNameIncompatible = true)
            } else {
                _uiState.value = _uiState.value.copy(isNameIncompatible = false)
            }
        }
    }

    /**
     * Saves the user name to the database.
     *
     * @param nameInput The user name input to save.
     * @param onNavigateToDevsIntro A lambda function to navigate to the developers introduction screen.
     */
    fun saveUserNameInDatabase(
        nameInput: String,
        onNavigateToDevsIntro: () -> Unit
        ) {
        viewModelScope.launch {
            if (nameInput.isNotEmpty() && nameInput.length <= 10) {
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
            } else {
                _uiState.value = _uiState.value.copy(userNameState = "INVALID USER NAME")
            }
        }
    }

    /**
     * Sets the visibility of the header in the UI state.
     *
     * @param isVisible Determines if the header should be visible.
     */
    fun setHeaderVisibilty(isVisible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(headerVisibility = isVisible)
        }
    }

    /**
     * Sets the visibility of the mascot image in the UI state.
     *
     * @param isVisible Determines if the mascot image should be visible.
     */
    fun setImageVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(imageVisibility = isVisible)
        }
    }

    /**
     * Sets the visibility of the name input field in the UI state.
     *
     * @param isVisible Determines if the name input field should be visible.
     */
    fun setTextFieldVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(textFieldVisibility = isVisible)
        }
    }
}