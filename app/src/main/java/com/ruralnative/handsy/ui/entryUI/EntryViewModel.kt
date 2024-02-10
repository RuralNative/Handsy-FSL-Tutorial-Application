package com.ruralnative.handsy.ui.entryUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    fun checkUserCountAndNavigate(
        navigateToInitial: () -> Unit,
        navigateToMain: () -> Unit
    ) {
        viewModelScope.launch {
            delay(2000) // wait for 2 seconds
            val isThereNoUser: Boolean = repository.isThereNoUser().first()
            if (!isThereNoUser) {
                navigateToInitial()
            } else {
                navigateToMain()
            }
        }
    }
}