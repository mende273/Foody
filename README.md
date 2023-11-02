# Foody
Real world Android application demonstrating the use of JetPack Compose with Koin, Ktor, Navigation Component and MVVM architecture.

Foody supports all windows sizes from phones in portrait and landscape to foldable phones and tablets. It also supports light and dark theme.

### Verify Koin configuration
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
- [Ktor](https://github.com/ktorio/ktor) - An asynchronous framework for creating microservices, web applications and more.
- [Coil](https://github.com/coil-kt/coil) - An image loading library for Android backed by Kotlin Coroutines.
- [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) - Provides an easy-to-use API and supports a wide range of formats for reflection-less serialization.
- [Material 3](https://m3.material.io/) - The latest version of Google’s open-source design system.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.

## Static Code Analysis
- [Ktlint](https://github.com/JLLeitschuh/ktlint-gradle) - A library for formatting Kotlin code according to official guidelines.
- [Detekt](https://github.com/detekt/detekt) - A static code analysis library for Kotlin.
