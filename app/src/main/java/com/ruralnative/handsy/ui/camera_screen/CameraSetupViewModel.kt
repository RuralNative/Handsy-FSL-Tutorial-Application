package com.ruralnative.handsy.ui.camera_screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CameraPermissionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CameraPermissionState())
    val uiState = _uiState.asStateFlow()

    @OptIn(ExperimentalPermissionsApi::class)
    fun setCameraPermissionState(state: PermissionState?) {
        _uiState.value = _uiState.value.copy(cameraPermissionState = state)
    }
}