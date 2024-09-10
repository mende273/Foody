plugins {
    alias(libs.plugins.foody.android.library)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "mende273.foody.image"
}

dependencies {

    // compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.preview)

    // coil
    implementation(libs.coil.compose)
}