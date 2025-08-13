<div align="center">

![Logo](/art/logo.png)

[![Android](https://img.shields.io/badge/Android-000000?style=for-the-badge&logo=android)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-000000?style=for-the-badge&logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Compose-000000?style=for-the-badge&logo=jetpackcompose)](https://developer.android.com/jetpack/compose)
[![Android Auto](https://img.shields.io/badge/Android%20Auto-000000?style=for-the-badge&logo=androidauto)](#)
[![WearOS](https://img.shields.io/badge/Wear%20OS-000000?style=for-the-badge&logo=WearOS)](#)
[![Beta](https://img.shields.io/badge/Beta-1.2.7-000000?style=for-the-badge&logo=googleplay&labelColor=000000)](https://play.google.com/store/apps/details?id=com.otix)

Otix is an Android application developed for the Android Auto platform that can display vehicle data and sensor information in real-time both on the vehicle’s screen and on a mobile device. The application provides users with essential vehicle information such as speed, speed unit, battery level, fuel percentage, and remaining range, while also enabling tracking of sensor data including accelerometer, gyroscope, and compass. Additionally, it integrates with hardware-based location data to show the current position on a map. Otix allows users to easily access comprehensive information about their vehicle during and after driving, offering a safer, more informed, and connected driving experience.

<a href="https://play.google.com/store/apps/details?id=com.otix">
    <img src="art/google-play-badge.png" width="200" alt="otix"/>
</a>
</div>

<br>

![Screenshot](/art/header.png)

### Android Auto
![Screenshot](/art/android-auto.png)

### Wear OS
![Screenshot](/art/wear.png)

### Phone
![Screenshot](/art/phone.png)

## Platforms
- Android Auto
- Android Wear
- Android Phone

## Features
- Car Info
- Car Fuel
- Car Map
- Car Location
- Car Sensors
- Car Trip
- Car Route Map

## Architecture
* [Clean Architecture](#clean-architecture)
* [Modularization](#modularization)
* [SOLID](#SOLID)
* [MVI](#mvi-pattern)
* [Jetpack Compose](#compose)
* [Navigation 3](#navigation3)

## Libraries
* [Koin](https://github.com/InsertKoinIO/koin)
* [SQLDelight](https://github.com/sqldelight/sqldelight)
* [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization)
* [Material3](https://m3.material.io/)
* [Firebase](https://firebase.google.com/)
* [osmdroid](https://github.com/osmdroid/osmdroid)

## Plugins
- detekt (Static Code Analysis)

## Others
* [RecomposeHighlighter](https://github.com/android/snippets/blob/main/compose/recomposehighlighter/src/main/java/com/example/android/compose/recomposehighlighter/RecomposeHighlighter.kt)

## Versions
- agp = "8.12.0"
- kotlin = "2.0.0"
- carApp (projected) = "1.7.0"
- google-services = 4.4.2
- firebase = "33.13.0"

## Package
<details>
<summary>
Show
</summary>

```
├── app
│   ├── build.gradle.kts
│   ├── google-services.json
│   ├── proguard-rules.pro
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com
│                   └── otix
│                       └── app
│                           └── OtixApplication.kt
├── automotive
│   ├── build.gradle.kts
│   ├── consumer-rules.pro
│   ├── proguard-rules.pro
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com
│                   └── otix
│                       └── automotive
│                           ├── ext
│                           │   └── VehicleExt.kt
│                           ├── manager
│                           │   ├── AccelerometerManager.kt
│                           │   ├── ClimateManager.kt
│                           │   ├── CompassManager.kt
│                           │   ├── EnergyLevelManager.kt
│                           │   ├── EnergyProfileFetcher.kt
│                           │   ├── EvStatusManager.kt
│                           │   ├── GyroscopeManager.kt
│                           │   ├── HardwareLocationManager.kt
│                           │   ├── MileageManager.kt
│                           │   ├── ModelFetcher.kt
│                           │   ├── SpeedManager.kt
│                           │   ├── TollCardManager.kt
│                           │   ├── VehicleInfoManager.kt
│                           │   └── VehicleStateManager.kt
│                           ├── screen
│                           │   ├── home
│                           │   │   ├── HomeScreen.kt
│                           │   │   ├── component
│                           │   │   │   ├── OtixGridItem.kt
│                           │   │   │   └── OtixRowItem.kt
│                           │   │   ├── tab
│                           │   │   │   ├── TabInfo.kt
│                           │   │   │   └── Tabs.kt
│                           │   │   └── template
│                           │   │       ├── FuelTemplate.kt
│                           │   │       ├── InfoTemplate.kt
│                           │   │       ├── LocationTemplate.kt
│                           │   │       └── SensorTemplate.kt
│                           │   └── permission
│                           │       ├── PermissionScreen.kt
│                           │       └── Permissions.kt
│                           ├── sender
│                           │   └── PhoneWearVehicleInfoSender.kt
│                           └── service
│                               ├── MainService.kt
│                               └── MainSession.kt
├── build.gradle.kts
├── buildSrc
│   ├── build.gradle.kts
│   └── src
│       └── main
│           └── kotlin
│               ├── Config.kt
│               ├── Extension.kt
│               └── Version.kt
├── config
│   ├── auto
│   │   └── auto.ini
│   ├── detekt
│   │   ├── baseline.xml
│   │   └── detekt.yml
├── core
│   ├── architecture
│   │   ├── build.gradle.kts
│   │   ├── consumer-rules.pro
│   │   ├── proguard-rules.pro
│   │   └── src
│   │       └── main
│   │           ├── AndroidManifest.xml
│   │           └── java
│   │               └── com
│   │                   └── otix
│   │                       └── core
│   │                           └── architecture
│   │                               ├── BaseViewModel.kt
│   │                               └── UiContract.kt
│   ├── build.gradle.kts
│   ├── extension
│   │   ├── build.gradle.kts
│   │   ├── consumer-rules.pro
│   │   ├── proguard-rules.pro
│   │   └── src
│   │       └── main
│   │           ├── AndroidManifest.xml
│   │           └── java
│   │               └── com
│   │                   └── otix
│   │                       └── core
│   │                           ├── compose
│   │                           │   └── RecomposeHighlighter.kt
│   │                           └── extension
│   │                               ├── AddressExt.kt
│   │                               ├── DefaultsExt.kt
│   │                               ├── IntentExt.kt
│   │                               ├── JsonExt.kt
│   │                               ├── LogExt.kt
│   │                               ├── PermissionExt.kt
│   │                               ├── RoundedExt.kt
│   │                               ├── StringExt.kt
│   │                               └── TimeExt.kt
│   ├── location
│   │   ├── build.gradle.kts
│   │   ├── consumer-rules.pro
│   │   ├── proguard-rules.pro
│   │   └── src
│   │       └── main
│   │           ├── AndroidManifest.xml
│   │           └── java
│   │               └── com
│   │                   └── otix
│   │                       └── core
│   │                           └── location
│   │                               ├── di
│   │                               │   └── LocationModule.kt
│   │                               └── manager
│   │                                   ├── LocationManager.kt
│   │                                   └── LocationManagerImpl.kt
│   ├── navigation
│   │   ├── build.gradle.kts
│   │   ├── consumer-rules.pro
│   │   ├── proguard-rules.pro
│   │   └── src
│   │       └── main
│   │           ├── AndroidManifest.xml
│   │           └── java
│   │               └── com
│   │                   └── otix
│   │                       └── core
│   │                           └── navigation
│   │                               ├── di
│   │                               │   └── NavigationModule.kt
│   │                               ├── navigator
│   │                               │   ├── Navigator.kt
│   │                               │   └── NavigatorImpl.kt
│   │                               └── route
│   │                                   ├── Connect.kt
│   │                                   ├── FuelCapacity.kt
│   │                                   ├── Home.kt
│   │                                   ├── Splash.kt
│   │                                   ├── TripDetail.kt
│   │                                   └── TripMap.kt
│   ├── resources
│   │   ├── build.gradle.kts
│   │   ├── consumer-rules.pro
│   │   ├── proguard-rules.pro
│   │   └── src
│   │       └── main
│   │           ├── AndroidManifest.xml
│   │           └── java
│   │               └── com
│   │                   └── otix
│   │                       └── core
│   │                           └── resources
│   │                               ├── alias
│   │                               │   └── TypeAlias.kt
│   │                               ├── date
│   │                               │   └── DateFormat.kt
│   │                               ├── di
│   │                               │   └── StringsModule.kt
│   │                               └── strings
│   │                                   ├── Strings.kt
│   │                                   └── StringsImpl.kt
│   ├── service
│   │   ├── build.gradle.kts
│   │   ├── consumer-rules.pro
│   │   ├── proguard-rules.pro
│   │   └── src
│   │       └── main
│   │           ├── AndroidManifest.xml
│   │           └── java
│   │               └── com
│   │                   └── otix
│   │                       └── service
│   │                           └── CarStatusService.kt
│   └── ui
│       ├── build.gradle.kts
│       ├── consumer-rules.pro
│       ├── proguard-rules.pro
│       └── src
│           └── main
│               ├── AndroidManifest.xml
│               └── java
│                   └── com
│                       └── otix
│                           └── core
│                               └── ui
│                                   ├── component
│                                   │   ├── OtixBadge.kt
│                                   │   ├── OtixBarChart.kt
│                                   │   ├── OtixButton.kt
│                                   │   ├── OtixCard.kt
│                                   │   ├── OtixDivider.kt
│                                   │   ├── OtixEmptyState.kt
│                                   │   ├── OtixHalfCircleProgressBar.kt
│                                   │   ├── OtixHorizontalProgressBar.kt
│                                   │   ├── OtixHorizontalSlider.kt
│                                   │   ├── OtixLineChart.kt
│                                   │   ├── OtixStatusBadge.kt
│                                   │   └── OtixTitle.kt
│                                   ├── ext
│                                   │   └── ModifierExt.kt
│                                   ├── provider
│                                   │   ├── LocalNavigator.kt
│                                   │   └── LocalStrings.kt
│                                   └── theme
│                                       ├── ColorScheme.kt
│                                       ├── Colors.kt
│                                       ├── Font.kt
│                                       ├── Shapes.kt
│                                       ├── Theme.kt
│                                       └── Typography.kt
├── gradle
│   ├── libs.versions.toml
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── mobile
│   ├── build.gradle.kts
│   ├── consumer-rules.pro
│   ├── core
│   │   ├── build.gradle.kts
│   │   ├── consumer-rules.pro
│   │   ├── proguard-rules.pro
│   │   └── ui
│   │       ├── build.gradle.kts
│   │       ├── consumer-rules.pro
│   │       ├── proguard-rules.pro
│   │       └── src
│   │           └── main
│   │               ├── AndroidManifest.xml
│   │               └── java
│   │                   └── com
│   │                       └── otix
│   │                           └── mobile
│   │                               └── core
│   │                                   └── ui
│   │                                       ├── component
│   │                                       │   ├── CompassCard.kt
│   │                                       │   ├── EnergyLevelCard.kt
│   │                                       │   ├── FuelCapacityCard.kt
│   │                                       │   ├── InfoCard.kt
│   │                                       │   ├── InfoHeader.kt
│   │                                       │   ├── InfoHorizontalCard.kt
│   │                                       │   ├── InfoListCard.kt
│   │                                       │   ├── RangeRemainingCard.kt
│   │                                       │   └── SpeedIndicatorCard.kt
│   │                                       └── scene
│   │                                           └── BottomSheetSceneStrategy.kt
│   ├── feature
│   │   ├── build.gradle.kts
│   │   ├── connect
│   │   │   ├── build.gradle.kts
│   │   │   ├── consumer-rules.pro
│   │   │   ├── presentation
│   │   │   │   ├── build.gradle.kts
│   │   │   │   ├── consumer-rules.pro
│   │   │   │   ├── proguard-rules.pro
│   │   │   │   └── src
│   │   │   │       └── main
│   │   │   │           ├── AndroidManifest.xml
│   │   │   │           └── java
│   │   │   │               └── com
│   │   │   │                   └── otix
│   │   │   │                       └── mobile
│   │   │   │                           └── feature
│   │   │   │                               └── connect
│   │   │   │                                   └── presentation
│   │   │   │                                       ├── ConnectScreen.kt
│   │   │   │                                       └── receiver
│   │   │   │                                           └── RegisterCarAppStatusReceiver.kt
│   │   │   └── proguard-rules.pro
│   │   ├── dashboard
│   │   │   ├── build.gradle.kts
│   │   │   ├── consumer-rules.pro
│   │   │   ├── presentation
│   │   │   │   ├── build.gradle.kts
│   │   │   │   ├── consumer-rules.pro
│   │   │   │   ├── proguard-rules.pro
│   │   │   │   └── src
│   │   │   │       └── main
│   │   │   │           ├── AndroidManifest.xml
│   │   │   │           └── java
│   │   │   │               └── com
│   │   │   │                   └── otix
│   │   │   │                       └── mobile
│   │   │   │                           └── feature
│   │   │   │                               └── dashboard
│   │   │   │                                   └── presentation
│   │   │   │                                       └── DashboardScreen.kt
│   │   │   └── proguard-rules.pro
│   │   ├── fuel
│   │   │   ├── build.gradle.kts
│   │   │   ├── consumer-rules.pro
│   │   │   ├── presentation
│   │   │   │   ├── build.gradle.kts
│   │   │   │   ├── consumer-rules.pro
│   │   │   │   ├── proguard-rules.pro
│   │   │   │   └── src
│   │   │   │       └── main
│   │   │   │           ├── AndroidManifest.xml
│   │   │   │           └── java
│   │   │   │               └── com
│   │   │   │                   └── otix
│   │   │   │                       └── mobile
│   │   │   │                           └── feature
│   │   │   │                               └── fuel
│   │   │   │                                   └── presentation
│   │   │   │                                       ├── di
│   │   │   │                                       │   └── FuelModule.kt
│   │   │   │                                       └── screen
│   │   │   │                                           ├── capacity
│   │   │   │                                           │   ├── FuelCapacityContract.kt
│   │   │   │                                           │   ├── FuelCapacityReducer.kt
│   │   │   │                                           │   ├── FuelCapacityRoot.kt
│   │   │   │                                           │   ├── FuelCapacitySheet.kt
│   │   │   │                                           │   └── FuelCapacityViewModel.kt
│   │   │   │                                           └── fuel
│   │   │   │                                               └── FuelScreen.kt
│   │   │   └── proguard-rules.pro
│   │   ├── map
│   │   │   ├── build.gradle.kts
│   │   │   ├── consumer-rules.pro
│   │   │   ├── domain
│   │   │   │   ├── build.gradle.kts
│   │   │   │   ├── consumer-rules.pro
│   │   │   │   ├── proguard-rules.pro
│   │   │   │   └── src
│   │   │   │       └── main
│   │   │   │           ├── AndroidManifest.xml
│   │   │   │           └── java
│   │   │   │               └── com
│   │   │   │                   └── otix
│   │   │   │                       └── mobile
│   │   │   │                           └── feature
│   │   │   │                               └── map
│   │   │   │                                   └── domain
│   │   │   │                                       └── tile
│   │   │   │                                           └── MapTileSource.kt
│   │   │   ├── presentation
│   │   │   │   ├── build.gradle.kts
│   │   │   │   ├── consumer-rules.pro
│   │   │   │   ├── proguard-rules.pro
│   │   │   │   └── src
│   │   │   │       └── main
│   │   │   │           ├── AndroidManifest.xml
│   │   │   │           └── java
│   │   │   │               └── com
│   │   │   │                   └── otix
│   │   │   │                       └── mobile
│   │   │   │                           └── feature
│   │   │   │                               └── map
│   │   │   │                                   └── presentation
│   │   │   │                                       ├── MapScreen.kt
│   │   │   │                                       └── component
│   │   │   │                                           ├── MapLocationItem.kt
│   │   │   │                                           └── MapView.kt
│   │   │   └── proguard-rules.pro
│   │   ├── sensor
│   │   │   ├── build.gradle.kts
│   │   │   ├── consumer-rules.pro
│   │   │   ├── presentation
│   │   │   │   ├── build.gradle.kts
│   │   │   │   ├── consumer-rules.pro
│   │   │   │   ├── proguard-rules.pro
│   │   │   │   └── src
│   │   │   │       └── main
│   │   │   │           ├── AndroidManifest.xml
│   │   │   │           └── java
│   │   │   │               └── com
│   │   │   │                   └── otix
│   │   │   │                       └── mobile
│   │   │   │                           └── feature
│   │   │   │                               └── sensor
│   │   │   │                                   └── presentation
│   │   │   │                                       └── SensorScreen.kt
│   │   │   └── proguard-rules.pro
│   │   ├── settings
│   │   │   ├── build.gradle.kts
│   │   │   ├── consumer-rules.pro
│   │   │   ├── presentation
│   │   │   │   ├── build.gradle.kts
│   │   │   │   ├── consumer-rules.pro
│   │   │   │   ├── proguard-rules.pro
│   │   │   │   └── src
│   │   │   │       └── main
│   │   │   │           ├── AndroidManifest.xml
│   │   │   │           └── java
│   │   │   │               └── com
│   │   │   │                   └── otix
│   │   │   │                       └── mobile
│   │   │   │                           └── feature
│   │   │   │                               └── settings
│   │   │   │                                   └── presentation
│   │   │   │                                       ├── di
│   │   │   │                                       │   └── SettingsModule.kt
│   │   │   │                                       └── screen
│   │   │   │                                           ├── SettingsContract.kt
│   │   │   │                                           ├── SettingsReducer.kt
│   │   │   │                                           ├── SettingsRoot.kt
│   │   │   │                                           ├── SettingsScreen.kt
│   │   │   │                                           ├── SettingsScreenController.kt
│   │   │   │                                           └── SettingsViewModel.kt
│   │   │   └── proguard-rules.pro
│   │   ├── splash
│   │   │   ├── build.gradle.kts
│   │   │   ├── consumer-rules.pro
│   │   │   ├── presentation
│   │   │   │   ├── build.gradle.kts
│   │   │   │   ├── consumer-rules.pro
│   │   │   │   ├── proguard-rules.pro
│   │   │   │   └── src
│   │   │   │       └── main
│   │   │   │           ├── AndroidManifest.xml
│   │   │   │           └── java
│   │   │   │               └── com
│   │   │   │                   └── otix
│   │   │   │                       └── mobile
│   │   │   │                           └── feature
│   │   │   │                               └── splash
│   │   │   │                                   └── presentation
│   │   │   │                                       └── SplashScreen.kt
│   │   │   └── proguard-rules.pro
│   │   └── trip
│   │       ├── build.gradle.kts
│   │       ├── consumer-rules.pro
│   │       ├── presentation
│   │       │   ├── build.gradle.kts
│   │       │   ├── consumer-rules.pro
│   │       │   ├── proguard-rules.pro
│   │       │   └── src
│   │       │       └── main
│   │       │           ├── AndroidManifest.xml
│   │       │           └── java
│   │       │               └── com
│   │       │                   └── otix
│   │       │                       └── mobile
│   │       │                           └── feature
│   │       │                               └── trip
│   │       │                                   └── presentation
│   │       │                                       ├── component
│   │       │                                       │   ├── TripCard.kt
│   │       │                                       │   └── TripLocationCard.kt
│   │       │                                       ├── di
│   │       │                                       │   └── TripModule.kt
│   │       │                                       └── screen
│   │       │                                           ├── detail
│   │       │                                           │   ├── TripDetailContract.kt
│   │       │                                           │   ├── TripDetailReducer.kt
│   │       │                                           │   ├── TripDetailRoot.kt
│   │       │                                           │   ├── TripDetailScreen.kt
│   │       │                                           │   └── TripDetailViewModel.kt
│   │       │                                           ├── map
│   │       │                                           │   ├── TripMapContract.kt
│   │       │                                           │   ├── TripMapReducer.kt
│   │       │                                           │   ├── TripMapRoot.kt
│   │       │                                           │   ├── TripMapScreen.kt
│   │       │                                           │   ├── TripMapViewModel.kt
│   │       │                                           │   ├── component
│   │       │                                           │   │   ├── TripMapInfoItem.kt
│   │       │                                           │   │   └── TripMapView.kt
│   │       │                                           │   └── info
│   │       │                                           │       └── TripMapInfoBar.kt
│   │       │                                           └── trip
│   │       │                                               ├── TripContract.kt
│   │       │                                               ├── TripReducer.kt
│   │       │                                               ├── TripRoot.kt
│   │       │                                               ├── TripScreen.kt
│   │       │                                               └── TripViewModel.kt
│   │       └── proguard-rules.pro
│   ├── proguard-rules.pro
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           └── java
│               └── com
│                   └── otix
│                       └── mobile
│                           ├── MobileActivity.kt
│                           ├── MobileNavHost.kt
│                           ├── empty
│                           │   └── EmptyScreen.kt
│                           └── home
│                               ├── HomeContent.kt
│                               ├── HomeScreen.kt
│                               ├── component
│                               │   ├── HomeNavBar.kt
│                               │   └── HomeTopBar.kt
│                               ├── page
│                               │   └── PageType.kt
│                               └── tab
│                                   └── Tabs.kt
├── settings.gradle.kts
├── shared
│   ├── build.gradle.kts
│   ├── data
│   │   ├── build.gradle.kts
│   │   ├── consumer-rules.pro
│   │   ├── proguard-rules.pro
│   │   └── src
│   │       └── main
│   │           ├── AndroidManifest.xml
│   │           ├── java
│   │           │   └── com
│   │           │       └── otix
│   │           │           └── shared
│   │           │               └── data
│   │           │                   ├── di
│   │           │                   │   └── DataLocalModule.kt
│   │           │                   └── local
│   │           │                       ├── entity
│   │           │                       │   ├── TripEntity.kt
│   │           │                       │   ├── VehicleEntity.kt
│   │           │                       │   └── VehiclePropertyEntity.kt
│   │           │                       ├── mapper
│   │           │                       │   ├── property
│   │           │                       │   │   ├── PropertyMapper.kt
│   │           │                       │   │   └── PropertyMapperImpl.kt
│   │           │                       │   ├── trip
│   │           │                       │   │   ├── TripMapper.kt
│   │           │                       │   │   └── TripMapperImpl.kt
│   │           │                       │   └── vehicle
│   │           │                       │       ├── VehicleMapper.kt
│   │           │                       │       └── VehicleMapperImpl.kt
│   │           │                       ├── repository
│   │           │                       │   ├── PropertyRepositoryImpl.kt
│   │           │                       │   ├── TripRepositoryImpl.kt
│   │           │                       │   └── VehicleRepositoryImpl.kt
│   │           │                       └── source
│   │           │                           ├── property
│   │           │                           │   ├── PropertyLocalDataSource.kt
│   │           │                           │   └── PropertyLocalDataSourceImpl.kt
│   │           │                           ├── trip
│   │           │                           │   ├── TripLocalDataSource.kt
│   │           │                           │   └── TripLocalDataSourceImpl.kt
│   │           │                           └── vehicle
│   │           │                               ├── VehicleLocalDataSource.kt
│   │           │                               └── VehicleLocalDataSourceImpl.kt
│   │           └── sqldelight
│   │               └── com
│   │                   └── otix
│   │                       └── shared
│   │                           └── data
│   │                               └── local
│   │                                   ├── Property.sq
│   │                                   ├── Trip.sq
│   │                                   └── Vehicle.sq
│   ├── domain
│   │   ├── build.gradle.kts
│   │   ├── consumer-rules.pro
│   │   ├── proguard-rules.pro
│   │   └── src
│   │       └── main
│   │           ├── AndroidManifest.xml
│   │           └── java
│   │               └── com
│   │                   └── otix
│   │                       └── shared
│   │                           └── domain
│   │                               ├── di
│   │                               │   └── DomainModule.kt
│   │                               ├── local
│   │                               │   ├── key
│   │                               │   │   └── PropertyKey.kt
│   │                               │   ├── repository
│   │                               │   │   ├── PropertyRepository.kt
│   │                               │   │   ├── TripRepository.kt
│   │                               │   │   └── VehicleRepository.kt
│   │                               │   └── usecase
│   │                               │       ├── GetLastVehicleUseCase.kt
│   │                               │       ├── GetPropertyUseCase.kt
│   │                               │       ├── GetTripListUseCase.kt
│   │                               │       ├── GetTripUseCase.kt
│   │                               │       ├── GetVehicleUseCase.kt
│   │                               │       ├── GetVehiclesUseCase.kt
│   │                               │       ├── RemoveVehicleUseCase.kt
│   │                               │       ├── SavePropertyUseCase.kt
│   │                               │       ├── SaveTripUseCase.kt
│   │                               │       └── SaveVehicleUseCase.kt
│   │                               ├── manager
│   │                               │   ├── TripManager.kt
│   │                               │   ├── TripManagerImpl.kt
│   │                               │   └── TripMergeConfig.kt
│   │                               ├── model
│   │                               │   ├── BatteryPercent.kt
│   │                               │   ├── EnergyLevel.kt
│   │                               │   ├── FuelPercent.kt
│   │                               │   ├── GeoLocation.kt
│   │                               │   ├── PhoneInfo.kt
│   │                               │   ├── Trip.kt
│   │                               │   ├── VehicleInfo.kt
│   │                               │   └── VehicleProperty.kt
│   │                               ├── resource
│   │                               │   └── Resource.kt
│   │                               └── type
│   │                                   ├── EvConnectorType.kt
│   │                                   ├── FuelType.kt
│   │                                   ├── TollCardState.kt
│   │                                   ├── UnitType.kt
│   │                                   └── VehicleState.kt
│   └── presentation
│       ├── build.gradle.kts
│       ├── consumer-rules.pro
│       ├── proguard-rules.pro
│       └── src
│           └── main
│               ├── AndroidManifest.xml
│               └── java
│                   └── com
│                       └── otix
│                           └── shared
│                               └── presentation
│                                   ├── di
│                                   │   └── SharedModule.kt
│                                   ├── mock
│                                   │   └── MockInfo.kt
│                                   └── shared
│                                       ├── SharedContract.kt
│                                       ├── SharedReducer.kt
│                                       └── SharedViewModel.kt
└── wear
│   ├── OtixApplication.kt
│   ├── di
│   │   └── WearModule.kt
│   ├── presentation
│   │   ├── MainActivity.kt
│   │   ├── MainContract.kt
│   │   ├── MainReducer.kt
│   │   ├── MainRoot.kt
│   │   ├── MainScreen.kt
│   │   └── MainViewModel.kt
│   └── service
│       └── WearVehicleInfoReceiverService.kt
```

</details>

## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
