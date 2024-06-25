import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kover)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sql.delight)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "mende273.foody"
    compileSdk = 34

    defaultConfig {
        applicationId = "mende273.foody"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
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

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    buildFeatures {
        compose = true
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

    composeCompiler {
        enableStrongSkippingMode = true
        reportsDestination = layout.buildDirectory.dir("compose_compiler")
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3.window.size)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.kotlinx.coroutines.test)
    implementation(libs.mockk)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)
    androidTestImplementation(libs.androidx.navigation.testing)

    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    testImplementation(libs.ktor.client.mock)

    implementation(libs.coil.compose)

    implementation(libs.android.driver)
    implementation(libs.coroutines.extensions)
}
