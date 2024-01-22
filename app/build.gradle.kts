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
    implementation("androidx.core:core-ktx:$androidCore")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$androidLifeCycle")
    implementation("com.google.ar:core:$googleCore")
    implementation(composeBom)
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.activity:activity-compose:$composeActivity")
    implementation("androidx.activity:activity-ktx:$composeActivity")
    implementation("androidx.constraintlayout:constraintlayout-compose:$composeConstraintLayout")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltNavigation")
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$navVersion")
    implementation("androidx.navigation:navigation-compose:$navVersion")

    // Unit Test Implementation Dependencies
    testImplementation("androidx.arch.core:core-common:$androidArchVersion")
    testImplementation("androidx.arch.core:core-runtime:$androidArchVersion")
    testImplementation("androidx.arch.core:core-testing:$androidArchVersion")
    testImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    testImplementation("androidx.room:room-testing:$roomVersion")
    testImplementation("junit:junit:$junit")

    // Debugging Implementation Dependencies
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Instrumentation Test Dependencies
    androidTestImplementation(composeBom)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    androidTestImplementation("androidx.navigation:navigation-testing:$navVersion")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0-beta01")

    //KSP-dependent Dependencies
    ksp("androidx.room:room-compiler:$roomVersion")
    ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")
    kspAndroidTest("com.google.dagger:hilt-android-compiler:$hiltVersion")
}