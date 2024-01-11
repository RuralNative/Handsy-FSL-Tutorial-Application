package com.ruralnative.handsy.ui.entryUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class EntryViewModel(private val repository: UserRepository): ViewModel() {

    // UI State fetched for modification inside ViewModel
    private val _uiState = MutableStateFlow(EntryState())
    //Backing property for outside classes' access to data
    val uiState: StateFlow<EntryState> = _uiState.asStateFlow()

    private var isThereAUser: Boolean = false

    fun checkUserCountAndNavigate(
        navigateToInitial: () -> Unit,
        navigateToMain: () -> Unit
    ) {
        viewModelScope.launch {
            delay(2000) // wait for 2 seconds
            isThereAUser = repository.isThereAUser().single()
            _uiState.value = uiState.value.copy(isThereAUserState = isThereAUser)

            if (isThereAUser) {
                navigateToMain()
            } else {
                navigateToInitial()
            }
        }
    }
}