
import com.hubstaff.gradle.deps.Config
import com.hubstaff.gradle.deps.Versions
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("com.hubstaff.gradle.compose.dependency")
    id("com.hubstaff.gradle.hilt.dependency")
    id("com.hubstaff.gradle.testing.dependency")
    id("com.hubstaff.gradle.common.dependency")
}
android {

    defaultConfig {
        applicationId = "com.netsoft.android.challenge"
        versionCode = 1
        versionName = "1.0"
    }

    kotlinOptions {
        jvmTarget = Config.kotlin_jvm
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

apply(plugin = "com.hubstaff.gradle.common.template")

dependencies {
    implementation(project(mapOf("path" to ":common:theme")))
    implementation(project(mapOf("path" to ":services:authentication")))
    implementation(project(mapOf("path" to ":common:resources")))
}

