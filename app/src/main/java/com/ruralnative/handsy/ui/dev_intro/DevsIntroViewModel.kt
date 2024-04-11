package com.ruralnative.handsy.ui.dev_intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * DevsIntroViewModel manages the logic for the [DevsIntroScreen].
 * It handles navigation to the main screen.
 */
@HiltViewModel
class DevsIntroViewModel @Inject constructor(
): ViewModel() {

    /**
     * Navigates to the HomeScreen after a specified delay.
     *
     * @param onNavigateToMain A lambda function to be called after the delay, which performs the navigation.
     */
    fun navigateToHome(
        onNavigateToMain: () -> Unit
    ) {
        viewModelScope.launch {
            delay(300)
            onNavigateToMain()
        }
    }
}