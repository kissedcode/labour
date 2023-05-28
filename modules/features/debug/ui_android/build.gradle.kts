plugins {
    alias(deps.plugins.android.library)
    alias(deps.plugins.kotlin.android)
    alias(deps.plugins.kotlinx.serialization)
}

android {
    compileSdk = 33
    namespace = "dev.kissed.labour.features.debug.ui_android"

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    
        // kotlin-android plugin
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = deps.versions.androidx.compose.get()
    }
}

dependencies {
    implementation(projects.modules.features.debug.core)

    implementation(platform(deps.androidx.compose.bom))
    implementation(deps.androidx.compose.ui.tooling)
    implementation(deps.androidx.compose.material)
    implementation(deps.androidx.compose.material.icons.extended)
}