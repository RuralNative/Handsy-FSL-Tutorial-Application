plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.google.dagger.hilt.android)
}

android {
    namespace = "com.ruralnative.handsy"
    testNamespace = "com.ruralnative.testhandsy"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.ruralnative.handsy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.ruralnative.handsy.HiltTestRunner"
        vectorDrawables.useSupportLibrary = true
        javaCompileOptions {
            annotationProcessorOptions {
                arguments [
                    "dagger.hilt.disabledModulesHaveInstallInCheck"
                ] = "true"
            }
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlinx.coroutines.FlowPreview"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
     }
    buildToolsVersion = "34.0.0"
}

hilt {
    enableAggregatingTask = true
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(libs.cronet.embedded)
    androidTestImplementation(libs.testng)
    //KSP-dependent Dependencies
    ksp(libs.room.compiler)
    ksp(libs.dagger.hilt.compiler)
    ksp(libs.androidx.lifecycle.compiler)
    kspTest(libs.dagger.hilt.android.testing)
    kspTest(libs.room.compiler)
    kspAndroidTest(libs.dagger.hilt.compiler)

    //Implementation Dependencies
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.android.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.android.lifecycle.runtime.ktx)
    implementation(libs.android.lifecycle.runtime.compose)
    implementation(libs.android.lifecycle.viewmodel.compose)
    implementation(enforcedPlatform(libs.compose.bom))
    implementation(libs.compose.animation)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.activity.compose)
    implementation(libs.compose.constraintlayout.compose)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.core)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.camera.core)
    implementation(libs.camera.camera2)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    implementation(libs.dagger.hilt.android)
    implementation(libs.google.ar.core)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.navigation.fragment)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    implementation(libs.mediapipe.tasks.vision)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.tensorflow.lite.task.vision.play.services)
    implementation(libs.play.services.tflite.gpu)
    implementation(libs.window)

    // Unit Test Implementation Dependencies
    testImplementation(libs.core.common)
    testImplementation(libs.core.runtime)
    testImplementation(libs.core.testing)
    testImplementation(libs.room.testing)
    testImplementation(libs.dagger.hilt.android.testing)
    testImplementation(libs.junit)

    // Debugging Implementation Dependencies
    debugImplementation(libs.compose.ui.test.manifest)
    debugImplementation(libs.compose.ui.tooling)

    // Instrumentation Test Dependencies
    androidTestImplementation(enforcedPlatform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.core.testing)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.dagger.hilt.android.testing)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.rules)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.uiautomator)
}