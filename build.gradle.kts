import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

buildscript {
    repositories {
        mavenLocal()
    }
}

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
                    freeCompilerArgs.addAll(listOf("-Xpartial-linkage=disable", "-opt-in=kotlin.RequiresOptIn", "-Xbackend-threads=8", "-Xcontext-receivers", "-jvm-target=21"))
                }
            }
        }
    }
}

abstract class GitVersionValueSource : ValueSource<String, ValueSourceParameters.None> {
    @get:Inject
    abstract val execOperations: ExecOperations

    override fun obtain(): String {
        val output = ByteArrayOutputStream()
        val error = ByteArrayOutputStream()
        execOperations.exec {
            commandLine("git rev-parse --short HEAD".split(" "))
            standardOutput = output
            errorOutput = error
        }

        return if (error.toByteArray().isNotEmpty()) {
            ""
        } else {
            "-" + String(output.toByteArray(), Charset.defaultCharset()).trim()
        }
    }
}
