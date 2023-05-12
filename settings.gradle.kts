@file:Suppress("UnstableApiUsage")

rootProject.name = "labour"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
    versionCatalogs {
        create("deps") {
            from(files("deps.toml"))
        }
    }
}

include(":app:appAndroid")
include(":core")
include(":shared:android:util")
include(":shared:kotlin:util")