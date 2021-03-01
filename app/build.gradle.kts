plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "me.aluceps.repofinder"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        dataBinding = true
    }
    buildTypes {
        getByName("release") {
            setMinifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    fileTree("dir" to "libs", "include" to listOf("*.jar"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Deps.Kotlin.version}")

    // support libraries
    implementation("androidx.appcompat:appcompat:1.0.2")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.browser:browser:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.google.android.material:material:1.1.0-alpha09")

    // DI
    implementation("com.google.dagger:dagger:${Deps.Dagger.version}")
    implementation("com.google.dagger:dagger-android:${Deps.Dagger.version}")
    implementation("com.google.dagger:dagger-android-support:${Deps.Dagger.version}")
    kapt("com.google.dagger:dagger-compiler:${Deps.Dagger.version}")
    kapt("com.google.dagger:dagger-android-processor:${Deps.Dagger.version}")

    // Rx
    implementation("io.reactivex.rxjava2:rxjava:2.2.6")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxkotlin:2.3.0")

    // Http Client
    implementation("com.squareup.retrofit2:retrofit:${Deps.Retrofit.version}")
    implementation("com.squareup.retrofit2:converter-gson:${Deps.Retrofit.version}")
    implementation("com.squareup.retrofit2:adapter-rxjava2:${Deps.Retrofit.version}")
    implementation("com.squareup.okhttp3:okhttp:${Deps.OkHttp.version}")
    implementation("com.squareup.okhttp3:logging-interceptor:${Deps.OkHttp.version}")
    implementation("com.google.code.gson:gson:2.8.5")

    // Image Loader
    implementation("com.github.bumptech.glide:glide:4.9.0")
    kapt("com.github.bumptech.glide:compiler:4.9.0")

    // Utility
    implementation("com.jakewharton.timber:timber:4.7.1")
}
