
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt")

}

android {
    namespace = "com.example.recipeapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.recipeapp"
        minSdk = 29
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

configurations.all {
    resolutionStrategy {
        force("com.google.guava:guava:23.5-jre")
        // hoặc version mới hơn, ví dụ: 32.1.2-jre
        exclude("com.google.guava", "listenablefuture")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("com.google.android.material:material:1.9.0")
    //circle image view
    implementation("de.hdodenhof:circleimageview:3.1.0")

    //scalable unit text size
    implementation("com.intuit.ssp:ssp-android:1.0.6")

    //scalable unit size
    implementation("com.intuit.sdp:sdp-android:1.0.6")

    //room database
    implementation("androidx.room:room-runtime:2.2.5")
    implementation("androidx.room:room-compiler:2.2.5")
    kapt("androidx.room:room-ktx:2.2.1")
    implementation("com.makeramen:roundedimageview:2.3.0")

    //crop image library
    implementation("com.vanniktech:android-image-cropper:4.5.0")

    //easy permission
    implementation("pub.devrel:easypermissions:3.0.0")

    //coroutines core
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:\$kotlin_version")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    // Gson converter (for parsing JSON)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Glide for image loading
    implementation ("com.github.bumptech.glide:glide:4.15.1")
}