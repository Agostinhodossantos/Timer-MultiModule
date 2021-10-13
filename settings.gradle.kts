pluginManagement {
    repositories {
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
includeBuild("plugins/deps")
rootProject.name = "HsChallenge"
include(":app")
include(":common:theme")
include(":common:resources")
include(":services:authentication")
include(":services:timer")
