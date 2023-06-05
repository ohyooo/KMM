plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.ohyooo.kmm"

    buildToolsVersion = Version.BUILD_TOOL_VERSION
    compileSdk = Version.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = "com.ohyooo.kmm"
        minSdk = Version.MIN_SDK_VERSION
        targetSdk = Version.TARGET_SDK_VERSION
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Libs.Androidx.activity)
}