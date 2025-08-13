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
    namespace = "$APPLICATION_ID.core.extension"
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
    implementation(libs.kotlinx.serialization.json)
}