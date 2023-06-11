import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("maven-publish")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }
    iosTarget("ios") {}

    val macosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::macosArm64
        else -> ::macosX64
    }
    macosTarget("macos") {}

    jvm {}

    cocoapods {
        summary = "Kotlin Multiplatform Demo Shared Library"
        homepage = "https://github.com/ohyooo"
        ios.deploymentTarget = Version.IOS_DEPLOYMENT_TARGET
        osx.deploymentTarget = Version.OSX_DEPLOYMENT_TARGET
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.Kotlin.coroutines)
            }
        }

        val androidMain by getting

        val iosMain by getting
        val iosTest by getting
        val macosMain by getting
        val macosTest by getting
        val jvmMain by getting
    }
}

android {
    namespace = "com.ohyooo.kmm.shared"
    buildToolsVersion = Version.BUILD_TOOL_VERSION
    compileSdk = Version.COMPILE_SDK_VERSION
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Version.MIN_SDK_VERSION
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
