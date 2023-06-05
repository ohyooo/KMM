import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version Libs.KOTLIN_VERSION apply false
    id("com.android.library") version Libs.AGP_VERSION apply false
}

allprojects {
    group = "com.ohyooo.kmm"
    version = "1.0.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform"){
    configure<KotlinMultiplatformExtension> {
        targets.withType<KotlinNativeTarget> {
            compilations.configureEach {
                compilerOptions.configure {
                    freeCompilerArgs.addAll(listOf("-Xpartial-linkage=disable", "-opt-in=kotlin.RequiresOptIn", "-Xbackend-threads=12", "-Xcontext-receivers", "-jvm-target=17"))
                }
            }
        }
    }
}