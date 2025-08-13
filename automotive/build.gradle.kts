import Config.Release.Module.IS_MINIFY_ENABLED
import Version.APPLICATION_ID
import Version.COMPILE_SDK
import Version.JVM_TARGET
import Version.MIN_SDK

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "$APPLICATION_ID.automotive"
    compileSdk = COMPILE_SDK

    defaultConfig {
        minSdk = MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = IS_MINIFY_ENABLED
            proguardFiles(
                getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        sourceSets.all {
            languageSettings {
                optIn("kotlin.experimental.ExperimentalTypeInference")
                languageVersion = "2.0"
                enableLanguageFeature("ContextReceivers")
            }
        }
    }
    
    kotlinOptions {
        jvmTarget = JVM_TARGET
    }

    lint {
        disable.add("NullSafeMutableLiveData")
    }
}

dependencies {
    implementation(project(":core:architecture"))
    implementation(project(":core:service"))
    implementation(project(":core:extension"))
    implementation(project(":core:resources"))
    implementation(project(":shared:data"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:presentation"))

    implementation(libs.androidx.car.app)
    implementation(libs.androidx.car.app.projected)
    implementation(libs.koin.android)
    implementation(libs.play.services.wearable)
}