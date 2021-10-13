// Top-level build file where you can add configuration options common to all sub-projects/modules. import com.hubstaff.gradle.dependencies.Versions



buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.1.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}



tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
