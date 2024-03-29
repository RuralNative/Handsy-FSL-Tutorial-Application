package com.ruralnative.handsy.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruralnative.handsy.ui.state.CameraLiveStreamState
import com.ruralnative.handsy.util.GestureRecognizerListener
import com.ruralnative.handsy.util.ResultBundle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CameraLiveStreamViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): ViewModel(), GestureRecognizerListener {

    private val _uiState = MutableStateFlow(CameraLiveStreamState(results = emptyList()))
    val uiState: StateFlow<CameraLiveStreamState> = _uiState.asStateFlow()

    override fun onError(error: String, errorCode: Int) {
        TODO("Update UI to Show Error")
    }

    override fun onResults(resultBundle: ResultBundle) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                results = resultBundle.results,
                inferenceTime = resultBundle.inferenceTime,
                inputImageHeight = resultBundle.inputImageHeight,
                inputImageWidth = resultBundle.inputImageWidth
            )
        }
    }
}