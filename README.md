## Kotlin Multiplatform demo App using a shared business logic:

The demo works on Android, iOS, macOS and JVM (currently Mac & Linux).

## Requirements

- JDK 21
- Android Studio [Dolphin (2021.3.1)](https://developer.android.com/studio)

# Build and run for iOS

```
./gradlew shared:podInstall
cd iosApp
pod install
open iosApp.xcworkspace
```

# Build and run for macOS

```
./gradlew shared:podInstall
cd macosApp
pod install
open macosApp.xcworkspace
```

# Build and run for JVM

```
./gradlew :jvmApp:run
```

# Build and run for Android

```
 ./gradlew :androidApp:installDebug
```
