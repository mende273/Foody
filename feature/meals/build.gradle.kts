plugins {
    alias(libs.plugins.foody.android.library)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "mende273.feature.meals"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":common"))

    implementation(libs.androidx.lifecycle.runtime.compose)

    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3.window.size)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)

    // koin
    implementation(libs.koin.androidx.compose)
}