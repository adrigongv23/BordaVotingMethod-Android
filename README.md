# Borda Voting Method Android App

See the [README_ES.md](README_ES.md) file for the Spanish version.

## Project Introduction
This Android application implements a voting system based on the **Borda Count Method**, combined with an additional **0–100 score-based voting system**.  
The app allows users to create and manage voting sessions, assign scores using sliders, and obtain a final ranking of options based on accumulated points.

The Borda method assigns points to each option based on its ranking position:
- The lowest-ranked option receives 0 points.
- The next receives 1 point.
- Points increase incrementally up to the top-ranked option.
- The option with the highest total score wins.

The application was developed as an individual academic project during an Erasmus stay at the **Frankfurt University of Applied Sciences** for the course **Mobile Devices**.

---

## Main Features
- Creation and management of voting sessions
- Input of votes using slider controls
- Borda-based voting system
- Alternative 0–100 score voting system
- Tracking vote counts
- Tracking number of participants
- Real-time score updates
- Final ranking display
- Reset voting session
- Cancel voting session

---

## Technical Stack
- Language: Kotlin
- Platform: Android
- Build System: Gradle (KTS)
- UI: XML Layouts
- Architecture: Multi-Activity structure

### Android Configuration
- Compile SDK: 34 (Android 14)
- Target SDK: 34 (Android 14)
- Min SDK: 28 (Android 9 Pie)

### Project Structure
- `MainActivity.kt` – main voting workflow
- `SecondActivity.kt` – secondary voting interface
- `ThirdActivity.kt` – results and ranking

Resources:
- Layouts → `app/src/main/res/layout`
- Drawables → `app/src/main/res/drawable`
- Values → `app/src/main/res/values`
- Manifest → `app/src/main/AndroidManifest.xml`

---

## How to Run the App

### Requirements
- Android Studio
- Android SDK 34
- Android Emulator or Physical Device

### Option 1 – Emulator
1. Open the project in Android Studio
2. Create or use a Pixel 5 virtual device
3. Run the application

### Option 2 – Physical Device
1. Enable Developer Mode on your Android phone
2. Enable USB Debugging
3. Connect via USB cable
4. Run the project from Android Studio

---

## Demo
A demonstration video of the application is included in the repository.
