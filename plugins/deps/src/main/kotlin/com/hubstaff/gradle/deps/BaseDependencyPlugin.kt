package com.hubstaff.gradle.deps

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BaseDependencyPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        addDependencies(target)
    }

    abstract fun addDependencies(project: Project)


}
