# Foody

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?logo=kotlin&logoColor=white&style=for-the-badge)
![Jetpack Compose](https://img.shields.io/static/v1?style=for-the-badge&message=Jetpack+Compose&color=4285F4&logo=Jetpack+Compose&logoColor=FFFFFF&label=)
![Android](https://img.shields.io/badge/Api%2026+-3DDC84?logo=android&logoColor=white&style=for-the-badge)
![Release](https://img.shields.io/github/v/release/mende273/foody?logo=github&color=lightblue&logoColor=white&labelColor=black&style=for-the-badge)

Real world Android application demonstrating the use of JetPack Compose with Koin, Ktor,SqlDelight, Navigation Component and MVVM architecture.

Foody supports almost all screen sizes from phones in portrait and landscape to foldable phones and tablets. It also supports light and dark theme.

## Screenshots
#### Portrait
<img src="/screenshots/s1_light_portrait.jpg" width="250"> <img src="/screenshots/s2_light_portrait.jpg" width="250"> <img src="/screenshots/s3_light_portrait.jpg" width="250">

<img src="/screenshots/s1_dark_portrait.jpg" width="250"> <img src="/screenshots/s2_dark_portrait.jpg" width="250"> <img src="/screenshots/s3_dark_portrait.jpg" width="250">

#### Landscape
<img src="/screenshots/s1_light_landscape.jpg" width="550"> 

<img src="/screenshots/s2_light_landscape_mobile.jpg" width="550"> 

<img src="/screenshots/s1_dark_landscape.jpg" width="550"> 

<img src="/screenshots/s2_dark_landscape.jpg" width="550">

#### Foldable / Tablet
<img src="/screenshots/s1_light_fold.jpg" width="550"> 

<img src="/screenshots/s1_dark_fold.jpg" width="550">

## Api
This project is using the API provided by [themealdb.com](https://www.themealdb.com/api.php)

## Compose Previews
The project uses <code>@PreviewScreenSizes</code>, <code>@PreviewLightDark</code> and <code>@PreviewFontScales</code> annotations from <code>androidx.compose.ui:ui-tooling-preview</code>

## PreBuild
The preBuild depends on 2 tasks: <b>ktlint</b> and <b>Detekt</b>. You can manually run the tasks with <code>./gradlew ktlintFormat</code> and <code>./gradlew detekt</code>

## Koin
#### Verify Koin configuration
```
@Test
fun checkKoinModule() {
  appModule.verify(
    extraTypes = listOf(
      io.ktor.client.engine.HttpClientEngine::class,
      io.ktor.client.HttpClientConfig::class
    )
  )
}
```

#### Koin Compile time check
```
ksp {
    arg("KOIN_CONFIG_CHECK","true")
}
```

## Coil - Image loader cache policy
```
ImageLoader(this).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(percent = 0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(percent = 0.03)
                    .directory(cacheDir)
                    .build()
            }
            .logger(DebugLogger())
            .build()
```

## Build With
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android’s modern toolkit for building native UI.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html#asynchronous-flow) - Official Kotlin's tooling for performing asynchronous work.
- [Android Jetpack](https://developer.android.com/jetpack) - Jetpack is a suite of libraries to help developers build state-of-the-art applications.
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) - Navigation Compose is a framework for navigating between composables while taking advantage of the Navigation component’s infrastructure and features.
- [Window size](https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes#window_size_classes) - Window size classes helps you design, responsive layouts.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - The ViewModel is designed to store and manage UI-related data in a lifecycle conscious way.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) - StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors.
- [Koin](https://insert-koin.io/) - A pragmatic lightweight dependency injection framework.
- [SqlDelight](https://cashapp.github.io/sqldelight/2.0.0/) - Generates typesafe Kotlin APIs from your SQL statements.
- [Ktor](https://github.com/ktorio/ktor) - An asynchronous framework for creating microservices, web applications and more.
- [Coil](https://github.com/coil-kt/coil) - An image loading library for Android backed by Kotlin Coroutines.
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - Provides an easy-to-use API and supports a wide range of formats for reflection-less serialization.
- [Material 3](https://m3.material.io/) - The latest version of Google’s open-source design system.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Gradle Version Catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog) - Maintain dependencies and plugins in a scalable way.

## Static Code Analysis
- [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - A library for formatting Kotlin code according to official guidelines.
- [Detekt](https://github.com/detekt/detekt) - A static code analysis library for Kotlin.

## Testing
- [MockEngine](https://api.ktor.io/ktor-client/ktor-client-mock/io.ktor.client.engine.mock/-mock-engine/index.html) - HttpClientEngine for writing tests without network.

## Code Coverage
- [Kover](https://github.com/Kotlin/kotlinx-kover) - a set of solutions for collecting test coverage of Kotlin code compiled for JVM and Android platforms.
