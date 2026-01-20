# SubTrack: Privacy-First Subscription Manager 

**SubTrack** is a high-performance, local-first Android application built with Kotlin and Jetpack Compose. It empowers users to track their recurring subscriptions and expenses without compromising their privacy. No cloud, no tracking—just pure local management.

## ✨ Features

- **📊 Insights & Visualization**: View spending habits through interactive Pie Charts categorized by service type (Entertainment, Utilities, etc.).
- **🔔 Smart Reminders**: Integrated with `WorkManager` to trigger local notifications 24 hours before a payment is due.
- **🔒 Biometric Security**: Secure your financial data with Fingerprint or Face ID using the Android BiometricPrompt API.
- **📂 Data Portability**: Export your entire subscription list to a `.csv` file for external backup or spreadsheet analysis.
- **🔍 Advanced Filtering**: Search and filter through expenses by month, category, or cost using optimized SQLite queries.
- **🌓 Modern UI**: Fully responsive Jetpack Compose interface with support for Material 3 and Dark Mode.

## 🛠 Tech Stack & Architecture

This project follows **Modern Android Development (MAD)** practices and Clean Architecture principles:

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Declarative UI)
- **Database**: [Room Persistence Library](https://developer.android.com/training/data-storage/room) (SQLite abstraction)
- **Architecture**: MVVM (Model-View-ViewModel) + Clean Architecture
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Background Tasks**: [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
- **Data Visualization**: [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
- **Concurrency**: Kotlin Coroutines & Flow


<img width="381" height="832" alt="image" src="https://github.com/user-attachments/assets/a435e7fd-9ea7-4472-b25c-419a4aa77f97" /><img width="399" height="874" alt="image" src="https://github.com/user-attachments/assets/82b48ca0-32d3-45dc-82ac-2c6a2de1c241" />

