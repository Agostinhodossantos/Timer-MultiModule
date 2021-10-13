package com.hubstaff.gradle.deps

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestingDependencyPlugin: BaseDependencyPlugin() {

    override fun addDependencies(project: Project) {
        val configurations = project.configurations
        if(configurations.findByName("testImplementation")==null){
            configurations.create("testImplementation")
        }
        if(configurations.findByName("androidTestImplementation")==null){
            configurations.create("androidTestImplementation")
        }
        if(configurations.findByName("debugImplementation")==null){
            configurations.create("debugImplementation")
        }
        project.dependencies {
            "testImplementation"(Dependencies.junit)
            "testImplementation"(Dependencies.mockk)
            "testImplementation"(Dependencies.mockkAndroid)
            "testImplementation"(Dependencies.coroutines_test)
            "testImplementation"(Dependencies.androidx_test_core)
            "debugImplementation"(Dependencies.androidx_ui_test_manifest)
            "androidTestImplementation"(Dependencies.androidx_ui_test_junit)
            "androidTestImplementation"(Dependencies.androidx_espresso)
            "androidTestImplementation"(Dependencies.androidx_test_ext)
        }
    }
}
