.

🌟 AndroidApp1 – Counter App with History
Kotlin • ViewBinding • RecyclerView • SharedPreferences
📌 Overview

This project is my custom Android application for Week 7 Assignment.
It includes counter functionality, step selection, history saving, deletion, and a refreshed UI based on the TapCounter2025 and Views2025 apps.

🔥 Features
🔢 Counter System

Increment counter

Decrement counter

Choose step value: 1 • 5 • 10 • 50

Long-press counter ➝ Reset to 0

💾 History Tracking

Save the current count

Timestamp included

History displayed in a scrollable list

Each item has a Delete button

New items appear at the top

🧹 History Auto-Clear

Every time the app starts:

Count resets to 0

History resets to empty

This ensures a clean, fresh start each time.

🎨 User Interface (UI)

Custom rounded buttons

Counter box with orange border

Purple modern theme

Gradient background

Clean layout using ConstraintLayout

🧰 Technologies Used
Technology	Purpose
Kotlin	Main programming language
Android Studio	IDE
ViewBinding	Replaces findViewById
RecyclerView	Display history
SharedPreferences	Store counter + history
JSON	Save list history
Drawable Resources	Buttons + backgrounds
📁 Project Structure
app/
 ├─ java/com/example/androidapp1/
 │   ├─ MainActivity.kt
 │   ├─ HistoryAdapter.kt
 │   ├─ HistoryItem.kt
 │   └─ SimpleItemSelectedListener.kt
 │
 └─ res/
     ├─ layout/activity_main.xml
     ├─ layout/item_history.xml
     ├─ drawable/counter_bg.xml
     ├─ drawable/rounded_button.xml
     └─ drawable/background_gradient.xml

🎞 Screenshots (Optional)

Add your screenshots after upload:

![Screenshot](screenshots/app_screen.png)

🧠 How the App Works
1️⃣ Counter Logic
currentCount += currentStep
persistCount()
updateCounterText()

2️⃣ Saving History

Saves value

Saves timestamp

Stored in JSON array

Added to RecyclerView

3️⃣ Clearing on Launch
historyList.clear()
prefs.remove(KEY_HISTORY)
prefs.remove(KEY_COUNT)

📦 Installation
Clone the repository:
git clone https://github.com/USERNAME/AndroidApp1.git

Open in Android Studio

Run on emulator or real device.

👩‍💻 Author

Tianna Martin
Mobile Web Developer & Android Development Student 💛
Passionate about UI, animations, and mobile app design.

✅ Assignment Requirements Completed

✔ Counter with increment/decrement
✔ Step selection
✔ Save count to history
✔ Delete history item
✔ Clean UI with rounded buttons
✔ Gradient background
✔ Use of Kotlin, RecyclerView, ViewBinding
✔ GitHub repo ready for submission
✔ Fully commented code
