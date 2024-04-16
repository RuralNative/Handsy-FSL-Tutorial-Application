package com.ruralnative.handsy.ui.camera_screen

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState


/**
 * Represents the state of the camera permission within the application.
 *
 * @property cameraPermissionState An instance of [PermissionState] that represents
 * the current state of the camera permission. This state can be used to determine
 * whether the permission is granted, denied, or if a rationale should be shown to the user.
 */
data class CameraPermissionState @OptIn(ExperimentalPermissionsApi::class) constructor(
    val cameraPermissionState: PermissionState? = null
)
