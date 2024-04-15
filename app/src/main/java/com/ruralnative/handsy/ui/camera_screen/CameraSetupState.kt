package com.ruralnative.handsy.ui.camera_screen

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

/**
 * Represents the state of the camera permission.
 *
 * @property cameraPermissionKey An integer key representing the camera permission status.
 * - `0` (default key): Permission is denied and requires no rationale.
 * - `1`: Permission is denied and requires rationale.
 * - `2`: Permission is granted.
 */
data class CameraPermissionState @OptIn(ExperimentalPermissionsApi::class) constructor(
    val cameraPermissionState: PermissionState? = null
)
