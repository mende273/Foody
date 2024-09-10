pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "Foody"
include(":app")
include(":core:domain")
include(":core:data")
include(":core:network")
include(":feature:favorites")
include(":common")
include(":image")
include(":feature:filtermeals")
include(":feature:mealdetails")
include(":feature:meals")
include(":feature:search")
include(":feature:random")
include(":feature:fullscreenimage")
