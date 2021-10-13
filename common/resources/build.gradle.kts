
import com.hubstaff.gradle.deps.Config
import com.hubstaff.gradle.deps.Versions
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.hubstaff.gradle.common.dependency")
}

android {
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

}

