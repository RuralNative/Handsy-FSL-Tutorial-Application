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

/**
 * ViewModel for managing camera permission state and actions for CameraSetup and its affiliated screens, Rationale and NoRationale.
 */
@HiltViewModel
class CameraPermissionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(CameraPermissionState())
    val uiState = _uiState.asStateFlow()

    /**
     * Updates the camera permission state in the ViewModel.
     *
     * This function is used to update the internal state of the camera permission based on the
     * provided [PermissionState]. The updated state is then emitted to the UI through the [uiState]
     * flow.
     *
     * @param state The new [PermissionState] to set. This can be null, indicating that the
     * permission state is not yet determined.
     */
    @OptIn(ExperimentalPermissionsApi::class)
    fun setCameraPermissionState(state: PermissionState?) {
        _uiState.value = _uiState.value.copy(cameraPermissionState = state)
    }

    /**
     * Returns a lambda function to request camera permission.
     *
     * This function generates a lambda that, when invoked, requests the camera permission using
     * the provided [PermissionState]. The lambda is intended to be used as an onClick handler
     * for a button or similar UI element.
     *
     * @param state The current [PermissionState] that should be used to request the permission.
     * @return A lambda function that requests the camera permission when invoked.
     */
    @OptIn(ExperimentalPermissionsApi::class)
    fun requestCameraPermission(state: PermissionState): () -> Unit {
        return { state?.launchPermissionRequest() }
    }

    /**
     * Opens the application settings screen.
     *
     * This function creates an intent to open the application settings screen for the current
     * application. The settings screen allows the user to manage permissions and other app-specific
     * settings.
     *
     * @param context The [Context] used to start the activity.
     */
    fun openApplicationSettings(context: Context) {
        val settingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        )
        return context.startActivity(settingsIntent)
    }
}