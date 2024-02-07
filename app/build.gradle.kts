kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.room")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
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
        kotlinCompilerExtensionVersion = "1.5.4"
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
    // Android Core Dependencies
    val androidCore = "1.12.0"
    val androidLifeCycle = "2.7.0"
    val androidArchVersion = "2.2.0"
    val googleCore = "1.41.0"
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    val composeActivity = "1.8.2"
    val composeConstraintLayout = "1.0.1"
    val hiltVersion = "2.50"
    val hiltNavigation = "1.1.0"
    val roomVersion = "2.6.1"
    val navVersion = "2.7.6"
    val junit = "4.13.2"

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
    implementation(composeBom)
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation(libs.compose.activity.compose)
    implementation(libs.compose.activity.ktx)
    implementation(libs.compose.constraintlayout.compose)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
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
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Instrumentation Test Dependencies
    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.rules)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.uiautomator)

    //KSP-dependent Dependencies
    ksp(libs.room.compiler)
    ksp(libs.hilt.android.compiler)
    ksp(libs.lifecycle.compiler)
    kspAndroidTest(libs.hilt.android.compiler)
}