package com.ruralnative.handsy.ui.entryUI

import android.util.Log
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
        Log.d("ENTRY_SCREEN", "checkUserCountAndNavigate() EXECUTED")
        viewModelScope.launch {
            delay(5000)
            val isThereNoUser: Boolean = repository.isThereNoUser().first()
            if (!isThereNoUser) {
                navigateToInitial()
            } else {
                navigateToMain()
            }
        }
    }
}