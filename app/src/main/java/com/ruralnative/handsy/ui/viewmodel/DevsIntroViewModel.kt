package com.ruralnative.handsy.ui.viewmodel

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
class DevsIntroViewModel @Inject constructor(
): ViewModel() {

    fun navigateToMain(
        onNavigateToMain: () -> Unit
    ) {
        viewModelScope.launch {
            delay(300)
            onNavigateToMain()
        }
    }
}