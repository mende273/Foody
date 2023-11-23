import com.android.build.gradle.internal.tasks.factory.dependsOn

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint") version "11.5.1"
    id("io.gitlab.arturbosch.detekt") version ("1.22.0")
    id("org.jetbrains.kotlinx.kover") version "0.7.4"
    id("kotlinx-serialization")
    id("com.google.devtools.ksp")
    id("app.cash.sqldelight")
}

android {
    namespace = "mende273.foody"
    compileSdk = 34

    defaultConfig {
        applicationId = "mende273.foody"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "META-INF/*"
        }
    }

    sqldelight {
        databases {
            create("Database") {
                packageName.set("mende273.foody")
            }
        }
    }

    ksp {
        arg("KOIN_CONFIG_CHECK", "true")
    }

    ktlint {
        this.android.set(true)
        this.ignoreFailures.set(false)
        this.reporters {
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
        }
    }

    detekt {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom("$projectDir/config/detekt.yml")
        baseline = file("$projectDir/config/baseline.xml")
    }

    tasks.preBuild {
        dependsOn("ktlintFormat", "detekt")
    }
    tasks.detekt.dependsOn("ktlintFormat")
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")

    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3:1.1.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    implementation("io.mockk:mockk:1.13.6")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")

    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.navigation:navigation-compose:2.7.5")
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.5")

    implementation("io.insert-koin:koin-androidx-compose:3.4.6")
    implementation("io.insert-koin:koin-androidx-compose-navigation:3.4.6")
    testImplementation("io.insert-koin:koin-test:3.4.0")
    testImplementation("io.insert-koin:koin-test-junit4:3.4.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("io.ktor:ktor-client-android:2.3.5")
    implementation("io.ktor:ktor-client-serialization:2.3.5")
    implementation("io.ktor:ktor-client-logging:2.3.5")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")

    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation("app.cash.sqldelight:android-driver:2.0.0")
    implementation("app.cash.sqldelight:coroutines-extensions:2.0.0")
}
