plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.notnot.PAMProject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.notnot.PAMProject"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation ("com.github.bumptech.glide:glide:4.13.0")
    implementation ("com.github.ismaeldivita:chip-navigation-bar:1.4.0")
    implementation(libs.firebase.database)
    implementation(libs.activity)
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation(libs.firebase.auth)
    implementation(platform("com.google.firebase:firebase-database:21.0.0"))
    implementation(platform("com.google.firebase:firebase-core:21.1.1"))
    implementation("net.gotev:uploadservice:4.7.0")
}