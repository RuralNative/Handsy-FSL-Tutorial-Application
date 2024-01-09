package com.ruralnative.handsy.ui.entryUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class EntryViewModel(private val repository: UserRepository): ViewModel() {
    fun checkUserCountAndNavigate(
        navigateToInitial: () -> Unit,
        navigateToMain: () -> Unit
    ) {
        viewModelScope.launch {
            delay(2000) // wait for 2 seconds
            val isThereUser: Boolean = repository.isThereAUser().single()
            if (isThereUser) {
                navigateToMain()
            } else {
                navigateToInitial()
            }
        }
    }
}