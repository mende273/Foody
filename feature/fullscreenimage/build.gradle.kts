plugins {
    alias(libs.plugins.foody.android.library)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "mende273.foody.feature.fullscreenimage"
}

dependencies {
    implementation(project(":image"))
    implementation(project(":common"))

    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3.window.size)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui.preview)
}