package com.hubstaff.gradle.deps

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CommonDependencyPlugin : BaseDependencyPlugin() {

    override fun addDependencies(project: Project) {
        val configurations = project.configurations
        if (configurations.findByName("implementation") == null) {
            configurations.create("implementation")
        }
        project.dependencies {
            "implementation"(Dependencies.timber)
        }
    }
}
