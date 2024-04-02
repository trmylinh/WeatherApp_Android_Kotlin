import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
val localProperties = Properties()
localProperties.load(FileInputStream(rootProject.file("local.properties")))
android {
    namespace = "com.example.weatherapp_android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weatherapp_android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // add dependencies
    // ViewModel and LiveData

    //fragment extension
    val fragment_version = "1.4.0"
    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    val lifecycle_version = "2.7.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    //scalable unit size
    implementation("com.intuit.sdp:sdp-android:1.1.0")

    //for getting json data
    implementation("com.android.volley:volley:1.2.1")

    //location - lat, lon
    implementation("com.google.android.gms:play-services-location:21.2.0")

    // glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


}