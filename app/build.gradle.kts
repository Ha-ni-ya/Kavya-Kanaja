plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.kavyakanaja"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kavyakanaja"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    // If your Android Studio template already added the Compose compiler plugin,
    // keep that default setup.


    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("androidx.compose.ui:ui:1.6.8")

    implementation("androidx.compose.material3:material3:1.2.1")

    implementation("androidx.compose.ui:ui-tooling-preview:1.6.8")
    implementation("androidx.datastore:datastore-preferences:1.1.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.8")

}