import com.hubstaff.gradle.deps.Config
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.hubstaff.gradle.compose.dependency")
}

android {
    kotlinOptions {
        jvmTarget = Config.kotlin_jvm
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = com.hubstaff.gradle.deps.Versions.compose
    }
}

apply(plugin = "com.hubstaff.gradle.common.template")

dependencies {
    implementation(project(mapOf("path" to ":common:resources")))
}

