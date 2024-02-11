kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.google.dagger.hilt.android)
}

room {
    schemaDirectory("$projectDir/schema")
}

android {
    namespace = "com.ruralnative.handsy"
    testNamespace = "com.ruralnative.testhandsy"
    compileSdk = 34
    buildFeatures {
        compose = true
        viewBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    defaultConfig {
        applicationId = "com.ruralnative.handsy"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.ruralnative.handsy.CustomTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += [
                    "room.schemaLocation":"$projectDir/schemas".toString(),
                "room.incremental":"true",
                "room.expandProjection":"true"]
            }
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
     }
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    //Implementation Dependencies
    implementation(libs.android.core.ktx)
    implementation(libs.android.lifecycle.common.java8)
    implementation(libs.android.lifecycle.viewmodel.ktx)
    implementation(libs.android.lifecycle.viewmodel.compose)
    implementation(libs.android.lifecycle.viewmodel.savedstate)
    implementation(libs.android.lifecycle.livedata.ktx)
    implementation(libs.android.lifecycle.runtime.ktx)
    implementation(libs.android.lifecycle.runtime.compose)
    implementation(libs.google.ar.core)
    implementation(enforcedPlatform(libs.compose.bom))
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.activity.compose)
    implementation(libs.compose.activity.ktx)
    implementation(libs.compose.constraintlayout.compose)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.core)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.dagger.hilt)
    implementation(libs.hilt.common)
    implementation(libs.hilt.work)
    implementation(libs.hilt.navigation)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.navigation.fragment)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.navigation.dynamic.features.fragment)
    implementation(libs.navigation.safe.args.gradle.plugin)

    // Unit Test Implementation Dependencies
    testImplementation(libs.core.common)
    testImplementation(libs.core.runtime)
    testImplementation(libs.core.testing)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.room.testing)
    testImplementation(libs.junit)

    // Debugging Implementation Dependencies
    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)

    // Instrumentation Test Dependencies
    androidTestImplementation(enforcedPlatform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.rules)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.uiautomator)

    //KSP-dependent Dependencies
    ksp(libs.room.compiler)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.hilt.compiler)
    ksp(libs.lifecycle.compiler)
    kspAndroidTest(libs.dagger.hilt.compiler)
    kspAndroidTest(libs.hilt.compiler)
}