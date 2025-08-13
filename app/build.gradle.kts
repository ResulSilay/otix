import Extension.AAB
import Extension.APK
import Config.Debug
import Config.Release
import Version.APPLICATION_ID
import Version.COMPILE_SDK
import Version.JVM_TARGET
import Version.MIN_SDK
import Version.TARGET_SDK
import Version.VERSION_CODE
import Version.VERSION_NAME
import com.android.build.api.variant.impl.VariantOutputImpl

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = APPLICATION_ID
    compileSdk = COMPILE_SDK

    defaultConfig {
        applicationId = APPLICATION_ID
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
        versionCode = VERSION_CODE
        versionName = VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = Debug.IS_MINIFY_ENABLED
            isShrinkResources = Debug.IS_SHRINK_RESOURCES
        }

        release {
            isMinifyEnabled = Release.IS_MINIFY_ENABLED
            isShrinkResources = Release.IS_SHRINK_RESOURCES

            proguardFiles(
                getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName(name = "debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(JVM_TARGET)
        targetCompatibility = JavaVersion.toVersion(JVM_TARGET)
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

    lint {
        disable.add("NullSafeMutableLiveData")
    }
}

androidComponents {
    onVariants { variant ->
        variant.outputs.forEach { output ->
            if (output is VariantOutputImpl) {
                output.outputFileName = "${variant.name}-v$VERSION_NAME-$VERSION_CODE.$APK"
            }
        }
    }
}

afterEvaluate {
    tasks.named("bundleRelease").configure {
        doLast {
            val bundleDir = file("$projectDir/release")
            val originalBundle = bundleDir.listFiles()?.firstOrNull { it.extension == AAB }

            if (originalBundle != null) {
                val newName = "release-v$VERSION_NAME-$VERSION_CODE.$AAB"
                originalBundle.renameTo(File(bundleDir, newName))
            }
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core:navigation"))
    implementation(project(":core:resources"))
    implementation(project(":core:location"))
    implementation(project(":automotive"))
    implementation(project(":mobile"))
    implementation(project(":mobile:feature:fuel:presentation"))
    implementation(project(":mobile:feature:trip:presentation"))
    implementation(project(":mobile:feature:settings:presentation"))
    implementation(project(":shared"))
    implementation(project(":shared:data"))
    implementation(project(":shared:domain"))
    implementation(project(":shared:presentation"))

    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics.ndk)
    implementation(libs.google.firebase.analytics)
}
