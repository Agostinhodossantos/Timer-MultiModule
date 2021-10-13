plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    mavenCentral()
    google()
}

dependencies{
    implementation("com.android.tools.build:gradle:7.0.0-beta04")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.10")

}


gradlePlugin {
    plugins.register(("com.hubstaff.gradle.deps")) {
            id = "com.hubstaff.gradle.deps"
            implementationClass = "com.hubstaff.gradle.deps.ExperimentDependenciesPlugin"
    }
}

gradlePlugin {
    plugins.register(("com.hubstaff.gradle.compose.dependency")) {
        id = "com.hubstaff.gradle.compose.dependency"
        implementationClass = "com.hubstaff.gradle.deps.ComposeDependencyPlugin"
    }
}

gradlePlugin {
    plugins.register(("com.hubstaff.gradle.hilt.dependency")) {
        id = "com.hubstaff.gradle.hilt.dependency"
        implementationClass = "com.hubstaff.gradle.deps.HiltDependencyPlugin"
    }
}

gradlePlugin {
    plugins.register(("com.hubstaff.gradle.testing.dependency")) {
        id = "com.hubstaff.gradle.testing.dependency"
        implementationClass = "com.hubstaff.gradle.deps.TestingDependencyPlugin"
    }
}
gradlePlugin {
    plugins.register(("com.hubstaff.gradle.common.dependency")) {
        id = "com.hubstaff.gradle.common.dependency"
        implementationClass = "com.hubstaff.gradle.deps.CommonDependencyPlugin"
    }
}

gradlePlugin {
    plugins.register(("com.hubstaff.gradle.common.template")) {
        id = "com.hubstaff.gradle.common.template"
        implementationClass = "com.hubstaff.gradle.deps.TemplateAndroidPlugin"
    }
}

