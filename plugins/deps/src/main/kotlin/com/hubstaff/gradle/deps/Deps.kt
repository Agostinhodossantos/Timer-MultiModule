package com.hubstaff.gradle.deps

import org.gradle.api.JavaVersion

object Config {
    val minSdk = 21
    val compileSdk = 31
    val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_11
    val buildTools = "30.0.3"
    val kotlin_jvm = "11"

    val ktlint = "0.38.0"

}

object Versions {
    val androidx_core = "1.5.0"
    val app_compat = "1.3.0"
    val material = "1.3.0"
    val compose = "1.0.2"
    val activity_compose = "1.3.1"
    val lifecycle = "2.4.0-alpha03"
    val nav_compose = "2.4.0-alpha05"
    val hilt_navigation_compose = "1.0.0-alpha03"
    val constraint_layout="1.0.0-beta02"
    val accompanist="0.18.0"

    val mockito = "3.12.4"
    val junit = "4.12"
    val espresso = "3.3.0"
    val androidx_junit_ext = "1.1.2"
    val androidx_test_core = "1.4.0"
    val mockk = "1.12.0"

    val coroutines = "1.4.3"

    val timber = "4.7.1"

    val hilt = "2.37"

    val gradle_android = "7.0.0-beta04"
    val gradle_kotlin = "1.5.10"
    val kotlin = "1.5.21"
}

object Dependencies {
    val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"
    val google_material = "com.google.android.material:material:${Versions.material}"
    val androidx_compose_animation = "androidx.compose.animation:animation:${Versions.material}"
    val androidx_compose_material_icons = "androidx.compose.material:material-icons-core:${Versions.material}"
    val androidx_compose_material_icons_ext = "androidx.compose.material:material-icons-extended:${Versions.material}"
    val androidx_compose_constraint_layout = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint_layout}"
    val accompanist_navigation_material = "com.google.accompanist:accompanist-navigation-material:${Versions.accompanist}"
    val androidx_compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    val androidx_compose_material = "androidx.compose.material:material:${Versions.compose}"
//    val androidx_compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    val androidx_compose_ui_tooling = "androidx.compose.ui:ui-tooling:1.0.0-beta09"
    val androidx_lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    val androidx_lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    val androidx_activity_compose =
        "androidx.activity:activity-compose:${Versions.activity_compose}"
    val androidx_navigation_compose =
        "androidx.navigation:navigation-compose:${Versions.nav_compose}"
    val androidx_compose_livedata = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"

    val coroutines_android =
        "implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hilt_kapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    val hilt_navigation_compose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hilt_navigation_compose}"

    val coroutines_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    val mockk = "io.mockk:mockk:${Versions.mockk}"
    val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    val junit = "junit:junit:${Versions.junit}"
    val androidx_test_core = "androidx.test:core:${Versions.androidx_test_core}"
    val androidx_test_ext = "androidx.test.ext:junit:${Versions.androidx_junit_ext}"
    val androidx_espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val androidx_ui_test_junit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    val androidx_ui_test_manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
}

