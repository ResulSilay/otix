import Config.Release.Module.IS_MINIFY_ENABLED
import Version.APPLICATION_ID
import Version.COMPILE_SDK
import Version.JVM_TARGET
import Version.MIN_SDK

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "$APPLICATION_ID.mobile"
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

    kotlinOptions {
        jvmTarget = JVM_TARGET
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:architecture"))
    implementation(project(":core:extension"))
    implementation(project(":core:navigation"))
    implementation(project(":core:resources"))
    implementation(project(":core:location"))

    implementation(project(":automotive"))

    implementation(project(":shared"))
    implementation(project(":shared:data"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:presentation"))

    implementation(project(":mobile:core:ui"))
    implementation(project(":mobile:feature"))
    implementation(project(":mobile:feature:splash:presentation"))
    implementation(project(":mobile:feature:connect:presentation"))
    implementation(project(":mobile:feature:dashboard:presentation"))
    implementation(project(":mobile:feature:fuel:presentation"))
    implementation(project(":mobile:feature:sensor:presentation"))
    implementation(project(":mobile:feature:map:presentation"))
    implementation(project(":mobile:feature:settings:presentation"))
    implementation(project(":mobile:feature:trip:presentation"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation3.ui.android)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}