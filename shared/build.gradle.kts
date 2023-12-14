import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.algp)
    alias(libs.plugins.ks)
    alias(libs.plugins.kmm)
    kotlin("native.cocoapods")
    alias(libs.plugins.jc)
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
        ios.deploymentTarget = "14.1"
        osx.deploymentTarget = "11.0"
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.coroutines)
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
    compileSdk = libs.versions.compile.sdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.min.sdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}
