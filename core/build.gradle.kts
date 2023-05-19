plugins {
    alias(deps.plugins.kotlin.multiplatform)
    alias(deps.plugins.android.library)
}

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.shared.kotlin.util)

                implementation(deps.kotlinx.coroutines.core)
                implementation(deps.kotlinx.serialization.json)
                implementation(deps.kotlinx.reflect)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(platform(deps.androidx.compose.bom.get().toString()))
//                implementation(platform("androidx.compose:compose-bom:2022.10.00")) // doesn't work for some reason
                implementation(deps.androidx.compose.ui.tooling)
                implementation(deps.androidx.compose.material)
                implementation(deps.androidx.compose.material.icons.extended)
            }
        }
    }
}

dependencies {
    implementation(projects.shared.android.util)
}

android {
    namespace = "dev.kissed.labour"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = deps.versions.androidx.compose.get()
    }
}