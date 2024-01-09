package com.ruralnative.handsy.ui.initialScreens

import androidx.lifecycle.ViewModel
import com.ruralnative.handsy.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class UserIntroViewModel(private val repository: UserRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UserIntroState())
    val uiState: StateFlow<UserIntroState> = _uiState.asStateFlow()

    //
    //Receive String from TextField and send to Database, then Navigate to Main Screen
}