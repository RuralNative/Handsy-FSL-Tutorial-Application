kotlin {
    jvmToolchain(17)
}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.room")
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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

dependencies {
    // Android Core Dependencies
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Android Compose
    val composeBom = platform("androidx.compose:compose-bom:2023.10.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Material Design 3
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Room Database Dependencies
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")

    // AndroidX JUNIT 4
    testImplementation("junit:junit:4.13.2")

    // AndroidX Arch Core
    testImplementation("androidx.arch.core:core-common:2.2.0")
    testImplementation("androidx.arch.core:core-runtime:2.2.0")
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // AndroidX Test Library
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")

    // AndroidX Espresso
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // AndroidX UI Automator
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0-beta01")

    // AndroidX Compose Test APIs
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}