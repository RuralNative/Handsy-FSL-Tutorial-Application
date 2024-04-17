package com.ruralnative.handsy.ui.camera_screen.components.camera_gesture_recognition

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CameraGestureRecognitionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _uiState = MutableStateFlow(CameraGestureRecognitionState(results = emptyList()))
    val uiState: StateFlow<CameraGestureRecognitionState> = _uiState.asStateFlow()
}