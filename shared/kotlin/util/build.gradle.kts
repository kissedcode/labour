plugins {
    alias(deps.plugins.kotlin.multiplatform)
    alias(deps.plugins.android.library)
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(deps.kotlinx.coroutines.core)
                api(deps.kotlinx.serialization.json)
                api(deps.kotlinx.reflect)
            }
        }
        val androidMain by getting {
        }
    }
}

android {
    namespace = "dev.kissed.kotlin.util"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
}