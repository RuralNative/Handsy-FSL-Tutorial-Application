package com.ruralnative.handsy.ui.entryUI

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.repository.UserRepository
import com.ruralnative.handsy.di.qualifiers.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryViewModel @Inject constructor (
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