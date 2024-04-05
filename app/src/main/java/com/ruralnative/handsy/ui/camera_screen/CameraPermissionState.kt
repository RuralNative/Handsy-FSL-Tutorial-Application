package com.ruralnative.handsy.ui.camera_screen

data class CameraPermissionState(
    /**
     * Int Key representing Camera Permission
     * If 0 (default key), Permission is denied and requires no rationale
     * If 1, Permission is denied and requires rationale
     * If 2, Permission is granted
     */
    val cameraPermissionKey: Int = 0
)
