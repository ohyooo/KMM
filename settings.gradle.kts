pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "Kotlin Multiplatform Demo"

include(":shared", ":androidApp", ":jvmApp")
