// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        // Other dependencies...
        classpath(libs.dagger.hilt.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.androidx.room) apply false
    alias(libs.plugins.google.dagger.hilt.android) apply false
}