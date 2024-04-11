package com.ruralnative.handsy.ui.entry

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * EntryViewModel is responsible for managing the [EntryScreen] logic with helper functions.
 */
@HiltViewModel
class EntryViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    /**
     * Checks if there are any users and navigates to the appropriate screen.
     *
     * @param navigateToUserIntro A lambda function to navigate to the UserIntroScreen
     * @param navigateToHome A lambda function to navigate to the HomeScreen.
     */
    fun checkUserCountAndNavigate(
        navigateToUserIntro: () -> Unit,
        navigateToHome: () -> Unit
    ) {
        Log.d("ENTRY_SCREEN", "checkUserCountAndNavigate() EXECUTED")
        viewModelScope.launch {
            delay(1000)
            val isThereNoUser: Boolean = repository.isThereNoUser()
            if (isThereNoUser) {
                navigateToUserIntro()
            } else {
                navigateToHome()
            }
        }
    }
}