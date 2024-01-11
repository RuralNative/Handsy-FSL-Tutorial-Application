package com.ruralnative.handsy.ui.initialScreens

import androidx.lifecycle.ViewModel
import com.ruralnative.handsy.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class UserIntroViewModel(private val repository: UserRepository): ViewModel() {

    //Mutable StateFlow for ViewModel access only
    private val _uiState = MutableStateFlow(UserIntroState())
    //Backing property for State for outside access
    val uiState: StateFlow<UserIntroState> = _uiState.asStateFlow()

    //Update State with real-time keyboard typing task
    fun updateUserNameState(newNameState: String) {
        _uiState.value = _uiState.value.copy(userNameState = newNameState)
    }

    //Receive String from TextField and send to Database, then Navigate to Main Screen

}