plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("kotlin-android")
}

android {
    namespace = "com.example.ngampusaman"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.ngampusaman"
        minSdk = 24
        targetSdk = 33
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
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")

    //intuit
    implementation ("com.intuit.sdp:sdp-android:1.0.6")
    implementation ("com.intuit.ssp:ssp-android:1.0.6")

    //gif
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.17")

    //room
    implementation ("androidx.room:room-runtime:2.6.0")
    annotationProcessor ("androidx.room:room-compiler:2.6.0")
    kapt ("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")

    //docx
    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '3.17'
    implementation group: 'org.apache.xmlbeans', name: 'xmlbeans', version: '3.1.0'
    implementation 'javax.xml.stream:stax-api:1.0'
    implementation 'com.fasterxml:aalto-xml:1.2.2'
}
