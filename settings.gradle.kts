pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "otix"

include(":app")

include(":wear")

include(":automotive")

include(":mobile")
include(":mobile:core")
include(":mobile:core:ui")
include(":mobile:feature")
include(":mobile:feature:splash")
include(":mobile:feature:splash:presentation")
include(":mobile:feature:dashboard")
include(":mobile:feature:dashboard:presentation")
include(":mobile:feature:fuel")
include(":mobile:feature:fuel:presentation")
include(":mobile:feature:sensor")
include(":mobile:feature:sensor:presentation")
include(":mobile:feature:map")
include(":mobile:feature:map:domain")
include(":mobile:feature:map:presentation")
include(":mobile:feature:trip")
include(":mobile:feature:trip:presentation")
include(":mobile:feature:settings")
include(":mobile:feature:settings:presentation")
include(":mobile:feature:connect")
include(":mobile:feature:connect:presentation")

include(":core")
include(":core:ui")
include(":core:architecture")
include(":core:service")
include(":core:navigation")
include(":core:extension")
include(":core:resources")
include(":core:location")

include(":shared")
include(":shared:data")
include(":shared:domain")
include(":shared:presentation")
