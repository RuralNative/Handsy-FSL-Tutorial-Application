package com.ruralnative.handsy.ui.camera_screen

/**
 * Represents the state of the camera permission.
 *
 * @property cameraPermissionKey An integer key representing the camera permission status.
 * - `0` (default key): Permission is denied and requires no rationale.
 * - `1`: Permission is denied and requires rationale.
 * - `2`: Permission is granted.
 */
data class CameraPermissionState(

    /**
     * An integer key representing the camera permission status.
     * - `0` (default key): Permission is denied and requires no rationale.
     * - `1`: Permission is denied and requires rationale.
     * - `2`: Permission is granted.
     */
    val cameraPermissionKey: Int = 0
)
