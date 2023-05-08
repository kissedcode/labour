plugins {
    alias(deps.plugins.android.application)
    alias(deps.plugins.kotlin.android)
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        applicationId = "dev.kissed.labour"

        versionCode = 1
        versionName = "0.1"

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    
        // kotlin-android plugin
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    
    signingConfigs {
        create("dev_release") {
            storeFile = file("sign/dev/dev_release.keystore")
            storePassword = "password"
            keyAlias = "key0"
            keyPassword = "password"
        }
    }
    
    buildTypes {
        debug {
            applicationIdSuffix  = ".debug"
            manifestPlaceholders["buildType"] = "debug"
        }
        release {
            manifestPlaceholders["buildType"] = "release"
            signingConfig = signingConfigs["dev_release"]
            isMinifyEnabled = true
            proguardFiles += getDefaultProguardFile("proguard-android-optimize.txt")
            proguardFiles += File("proguard-rules.pro")
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

    "1.5.1".also { jetpackAppcompatVersion ->
        implementation("androidx.appcompat:appcompat:$jetpackAppcompatVersion")
        implementation("androidx.appcompat:appcompat-resources:$jetpackAppcompatVersion")
    }
    
    implementation(deps.androidx.activity.ktx)
    implementation(deps.androidx.activity.compose)
    
    implementation(deps.google.android.material)

    implementation(platform(deps.androidx.compose.bom))
    implementation(deps.androidx.compose.ui.tooling)
    implementation(deps.androidx.compose.material)
    implementation(deps.androidx.compose.material.icons.extended)
}