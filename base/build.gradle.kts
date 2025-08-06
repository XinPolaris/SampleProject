plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.huaxi.dev.base"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    api("androidx.viewpager2:viewpager2:1.1.0")
    api("androidx.annotation:annotation:1.8.2")
    api("androidx.lifecycle:lifecycle-process:2.6.1")

    api("com.github.bumptech.glide:glide:4.15.1")
    api("com.github.bumptech.glide:okhttp3-integration:4.15.1")
    api("jp.wasabeef:glide-transformations:4.3.0")

    api("com.squareup.okhttp3:okhttp:4.11.0")
    api("com.squareup.okhttp3:logging-interceptor:4.11.0")
    api("com.google.code.gson:gson:2.11.0")

    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")

    api("org.greenrobot:greendao:3.3.0")

    api("com.tencent:mmkv:1.3.1")
}