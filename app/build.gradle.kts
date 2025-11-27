// Add this plugins block at the top of your app/build.gradle.kts
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    // ... existing configuration like compileSdk, defaultConfig, etc.

    buildFeatures {
        // This line enables View Binding, which generates the Binding classes
        // (like ItemHistoryBinding) that your Adapter is currently missing.
        viewBinding = true
        namespace = "com.example.androidapp1"
        compileSdk = 36

        defaultConfig {
            applicationId = "com.example.androidapp1"
            minSdk = 23
            targetSdk = 36
            versionCode = 1
            versionName = "1.0"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

// Your dependencies block would go here, for example:
// dependencies {
//     implementation(libs.androidx.core.ktx)
//     implementation(libs.androidx.appcompat)
//     ...
// }
// In /Users/abdulmohammadbaig/Desktop/app/build.gradle.kts

// ... (android block)

dependencies {
    // Add this line to include the Material Components library
    implementation(libs.material)

    // Other existing dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
