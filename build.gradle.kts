// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        // Other dependencies...
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")
    }
}

plugins {
    id("com.android.application") version "8.2.1" apply false
    id("com.android.library") version "8.2.1" apply false

    id("org.jetbrains.kotlin.android") version "1.9.20" apply false

    id("com.google.devtools.ksp") version "1.9.20-1.0.13" apply false

    id("androidx.room") version "2.6.1" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
}