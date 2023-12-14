import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.agp) apply false
    alias(libs.plugins.algp) apply false
    alias(libs.plugins.ks) apply false
    alias(libs.plugins.kgp) apply false
    alias(libs.plugins.kmm) apply false
    alias(libs.plugins.jc) apply false
}

allprojects {
    group = "com.ohyooo.kmm"
    version = "1.0.0"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
    configure<KotlinMultiplatformExtension> {
        targets.withType<KotlinNativeTarget> {
            compilations.configureEach {
                compilerOptions.configure {
                    freeCompilerArgs.addAll(listOf("-Xpartial-linkage=disable", "-opt-in=kotlin.RequiresOptIn", "-Xbackend-threads=12", "-Xcontext-receivers", "-jvm-target=21"))
                }
            }
        }
    }
}