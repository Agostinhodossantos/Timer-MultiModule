package com.hubstaff.gradle.deps

import com.hubstaff.gradle.deps.Config.compileSdk
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class TemplateAndroidPlugin: BaseAndroidPlugin() {

    override fun apply(target: Project) {
        with(target){
            configureAndroid()
        }
    }

    private fun Project.configureAndroid(){
        android.run {
            compileSdkVersion(Config.compileSdk)
            buildToolsVersion(Config.buildTools)
            defaultConfig {
                minSdk = Config.minSdk
                targetSdk = Config.targetSdk
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
                vectorDrawables{
                    useSupportLibrary=true
                }
            }
            buildTypes {
                getByName("debug") {
                    isMinifyEnabled = false
                    debuggable(true)
                }
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
            compileOptions {
                sourceCompatibility = Config.javaVersion
                targetCompatibility =  Config.javaVersion
            }

            flavorDimensions("default")
            productFlavors {
                create("general") {
                    dimension = "default"
                }
                create("integration") {
                    dimension = "default"
                }
            }
            packagingOptions {
                exclude ("META-INF/AL2.0")
                exclude("META-INF/LGPL2.1")
            }

        }
    }
}
