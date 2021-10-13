import com.hubstaff.gradle.deps.Config
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("com.hubstaff.gradle.hilt.dependency")
    id("com.hubstaff.gradle.common.dependency")
}

android {
    kotlinOptions {
        jvmTarget = Config.kotlin_jvm
    }
}

apply(plugin = "com.hubstaff.gradle.common.template")

dependencies {
    implementation(com.hubstaff.gradle.deps.Dependencies.androidx_compose_ui_tooling)
}

