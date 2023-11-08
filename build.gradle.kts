// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("com.android.library") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.21"
    id("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
    id("app.cash.sqldelight") version "2.0.0" apply false
}