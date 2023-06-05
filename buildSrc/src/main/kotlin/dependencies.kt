object Version {
    const val MIN_SDK_VERSION = 19
    const val TARGET_SDK_VERSION = 33
    const val COMPILE_SDK_VERSION = 33
    const val BUILD_TOOL_VERSION = "33.0.2"
    const val NDK_VERSION = "25.2.9519653"
    const val CMAKE_VERSION = "3.22.1"

    const val IOS_DEPLOYMENT_TARGET= "14.1"
    const val OSX_DEPLOYMENT_TARGET= "11.0"
}

object Libs {
    const val KOTLIN_VERSION = "1.9.0-Beta"
    const val AGP_VERSION = "8.0.2"

    object Kotlin : Deps() {
        private const val coroutinesVersion = "1.7.1"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion".add()
    }

    object Androidx : Deps() {
        val activity = "androidx.activity:activity:1.7.2".add()
    }

    abstract class Deps {
        val list = ArrayList<String>()

        fun String.add(): String {
            list.add(this)
            return this
        }
    }

    val deps = Kotlin.list + Androidx.list
}
