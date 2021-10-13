package com.hubstaff.gradle.deps

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltDependencyPlugin : BaseDependencyPlugin() {
    override fun addDependencies(project: Project) {
        val configurations = project.configurations
        if (configurations.findByName("implementation") == null) {
            configurations.create("implementation")
        }
        if (configurations.findByName("kapt") == null) {
            configurations.create("kapt")
        }
        project.dependencies {
            "implementation"(Dependencies.hilt_android)
            "implementation"(Dependencies.hilt_navigation_compose)
            "kapt"(Dependencies.hilt_kapt)
        }
    }
}
