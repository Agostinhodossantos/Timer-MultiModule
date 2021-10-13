package com.hubstaff.gradle.deps

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ComposeDependencyPlugin : BaseDependencyPlugin() {



    override fun addDependencies(project: Project) {
        val configurations = project.configurations
        if (configurations.findByName("implementation") == null) {
            configurations.create("implementation")
        }
        project.dependencies {
            "implementation"(Dependencies.androidx_core)
            "implementation"(Dependencies.androidx_app_compat)
            "implementation"(Dependencies.google_material)
            "implementation"(Dependencies.androidx_compose_ui)
            "implementation"(Dependencies.androidx_compose_material)
            "implementation"(Dependencies.androidx_compose_ui_tooling)
            "implementation"(Dependencies.androidx_lifecycle_runtime)
            "implementation"(Dependencies.androidx_lifecycle_livedata)
            "implementation"(Dependencies.androidx_activity_compose)
            "implementation"(Dependencies.androidx_compose_livedata)
            "implementation"(Dependencies.androidx_navigation_compose)
            "implementation"(Dependencies.androidx_compose_constraint_layout)
            "implementation"(Dependencies.accompanist_navigation_material)

        }
    }
}
