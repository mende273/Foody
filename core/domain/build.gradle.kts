plugins {
    alias(libs.plugins.foody.android.library)
}

android {
    namespace = "mende273.foody.core.domain"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}