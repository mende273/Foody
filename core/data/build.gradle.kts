plugins {
    alias(libs.plugins.foody.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sql.delight)
}

android {
    namespace = "mende273.foody.core.data"

    sqldelight {
        databases {
            create("Database") {
                packageName.set("mende273.foody.core.data")
            }
        }
    }
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))

    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    // sqldelight
    implementation(libs.app.cash.sqldelight)
    implementation(libs.app.cash.sqldelight.coroutines.extensions)

    // ktor
    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    testImplementation(libs.ktor.client.mock)

    // koin
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.androidx.compose.navigation)
    testImplementation(libs.koin.test)
    testImplementation(libs.koin.test.junit4)
}