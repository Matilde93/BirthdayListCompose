import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version ("1.6.10-1.0.2")
    id("com.google.gms.google-services")
}
kotlin{
    sourceSets{
        debug{
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release{
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}


android {
    namespace = "com.example.birthdaylistcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.birthdaylistcompose"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}

configurations {
    all {
        exclude ( "xmlpull", "xmlpull")
        exclude ("xpp3", "xpp3")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-safe-args-generator:2.7.5")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Import the BoM for the Firebase platform
    implementation (platform("com.google.firebase:firebase-bom:31.0.0"))

    // Declare the dependency for the Firebase Authentication library
    implementation ("com.google.firebase:firebase-auth-ktx")

    // Firebase Authentication
    implementation ("com.google.firebase:firebase-auth-ktx:22.3.0")

    // Firebase Cloud Firestore
    implementation ("com.google.firebase:firebase-firestore-ktx:24.9.1")

    // Dagger-Hilt
    implementation ("com.google.dagger:hilt-android:2.48")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // ViewModel utilities for Compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Navigation
    implementation ("androidx.navigation:navigation-compose:2.7.5")

    implementation ("androidx.ui:ui-foundation:0.1.0-dev03")

    implementation ("androidx.compose.material3:material3:1.2.0-alpha12")

    implementation("io.github.raamcosta.compose-destinations:core:1.1.2-beta")
    ksp("io.github.raamcosta.compose-destinations:core:1.1.2-beta")


    implementation ("xmlpull:xmlpull:1.1.3.1")
    implementation ("xpp3:xpp3:1.1.4c")

}
