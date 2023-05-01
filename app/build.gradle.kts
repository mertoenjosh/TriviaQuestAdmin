plugins {
    id("kotlin-kapt")
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.mertoenjosh.questprovider"
    compileSdk= 33

    defaultConfig {
        applicationId = "com.mertoenjosh.questprovider"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(Dependencies.androidCore)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.timber)
    // compose
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeMaterialIcons)
    implementation(Dependencies.composeRuntimeLivecycle)
    implementation(Dependencies.composeRuntimeLivedata)
    implementation(Dependencies.composePaging)
    implementation(Dependencies.navigation)
    // kotlin
    implementation(Dependencies.kotlinCoroutines)
    implementation(Dependencies.kotlinSerialization)
    implementation(Dependencies.kotlinSerializationConverter)
    implementation(Dependencies.coil)
    // retrofit
    implementation (Dependencies.retrofit) // retrofit
    implementation(Dependencies.gsonConverter) // retrofit->gson
    implementation(Dependencies.okHttp) // logging
    // room
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomPaging)
    // Dagger - hilt
    implementation(Dependencies.daggerHilt)
    kapt(Dependencies.daggerCompiler) // checkout this
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltNavigation)
    // Firebase
    implementation(platform(Dependencies.firebaseBOM))
    implementation(Dependencies.crashlitics)
    implementation(Dependencies.crashlitics)
    // testing
    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.jUnitExt)
    androidTestImplementation(Dependencies.espresso)
    androidTestImplementation(Dependencies.composeUiTest)
    debugImplementation(Dependencies.composeUiTestTooling)
    debugImplementation(Dependencies.composeUiTestManifest)
    testImplementation(Dependencies.roomTesting)
}