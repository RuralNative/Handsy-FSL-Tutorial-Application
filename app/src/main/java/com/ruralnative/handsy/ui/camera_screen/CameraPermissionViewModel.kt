package com.ruralnative.handsy.ui.camera_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
}