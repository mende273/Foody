plugins {
    alias(libs.plugins.foody.android.library)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "mende273.foody.common"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":image"))

    implementation(libs.androidx.core.ktx)

    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.material3.window.size)
    implementation(libs.compose.ui.preview)
    implementation(libs.compose.material3)
}