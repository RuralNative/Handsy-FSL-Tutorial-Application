package com.ruralnative.handsy.ui.camera_screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
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

    @OptIn(ExperimentalPermissionsApi::class)
    fun requestCameraPermission(state: PermissionState): () -> Unit {
        return { state?.launchPermissionRequest() }
    }

    fun openApplicationSettings(context: Context) {
        val settingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        )
        return context.startActivity(settingsIntent)
    }
}