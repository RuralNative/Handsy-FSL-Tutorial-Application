package com.ruralnative.handsy.ui.camera_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraPermissionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CameraPermissionState())
    val uiState = _uiState.asStateFlow()

    /**
     * Updates the camera permission state with the given key.
     *
     * @param key The key representing the new camera permission state.
     * - `0`: Permission is denied and requires no rationale.
     * - `1`: Permission is denied and requires rationale.
     * - `2`: Permission is granted.
     */
    fun setCameraPermissionKey(key: Int) {
        _uiState.value = CameraPermissionState(key)
    }

    /**
     * Initializes the camera screen based on the current camera permission state.
     *
     * This function listens to changes in the camera permission state and navigates to the appropriate screen
     * based on the permission key. It uses a coroutine launched in the ViewModel's scope to collect the latest
     * state from the [uiState] flow and perform navigation accordingly.
     *
     * @param navigateToCameraScreen A lambda function to navigate to the camera screen.
     * @param navigateToRationaleScreen A lambda function to navigate to the rationale screen.
     * @param navigateToDefaultScreen A lambda function to navigate to the default screen.
     */
    fun initializeCameraScreen(
        navigateToCameraScreen: () -> Unit,
        navigateToRationaleScreen: () -> Unit,
        navigateToDefaultScreen: () -> Unit
    ) {
        viewModelScope.launch {
            uiState.collectLatest { state ->
                when (state.cameraPermissionKey) {
                    0 -> navigateToDefaultScreen()
                    1 -> navigateToRationaleScreen()
                    2 -> navigateToCameraScreen()
                }
            }
        }
    }
}