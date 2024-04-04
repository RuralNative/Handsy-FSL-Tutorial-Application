package com.ruralnative.handsy.ui.entry

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
            delay(500)
            val isThereNoUser: Boolean = repository.isThereNoUser()
            if (isThereNoUser) {
                navigateToInitial()
            } else {
                navigateToMain()
            }
        }
    }
}