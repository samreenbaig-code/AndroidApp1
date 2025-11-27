🌟 AndroidApp1 – Counter App with History (Kotlin + ViewBinding)

This project is my custom Android application created for Assignment – Week 7 Android Development.
The app recreates the core features of the TapCounter2025 and Views2025 exercises, but with improved UI, history saving, and data persistence.
🚀 App Features
🔢 Counter System

Increment & decrement the counter

Adjustable step values (1, 5, 10, 50)

Long-press the counter to reset to 0

💾 Save History

Save the current count with timestamp

Scrollable list of saved entries

Each saved item includes:

Count value

Date + time saved

A delete button

🧹 Auto-clear History

Every time the app launches, the history starts fresh

Ensures clean workspace for each use

🎨 Custom UI / Styling

Gradient background

Rounded buttons with custom drawable

Styled counter box

ViewBinding used instead of findViewById

Fully responsive layout for all screens

🛠️ Technologies Used

Kotlin

Android Studio

ViewBinding

RecyclerView + Adapter

SharedPreferences

JSON storage for history

ConstraintLayout

Custom Drawable Resources

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

📸 Screenshots

(Add your emulator screenshots here after uploading to GitHub)

Example:

![App Screenshot](screenshots/screen1.png)

🔧 How the App Works
1. Counter Management

Buttons adjust counter based on selected step.

Current value is always shown in the big counter box.

2. Saving History

"Save" records the value + timestamp

A new card appears at the top of the RecyclerView

3. Data Storage

Counter, Step, and History saved using:

SharedPreferences + JSON

4. Reset / Fresh Start

On app launch:

historyList.clear()
prefs.remove(KEY_HISTORY)
prefs.remove(KEY_COUNT)


App always starts with:

Count = 0
Empty history

📦 Installation

Clone the repository:

git clone https://github.com/USERNAME/AndroidApp1.git


Open in Android Studio

Run on emulator or physical device

🧑‍💻 Author

Tianna Martin
Mobile Web Developer & Android Student
💜 Passionate about learning and building real apps

📚 Assignment Requirements Completed

✔ Create a brand-new Android project
✔ Replicate TapCounter2025 functionality
✔ Custom UI with drawables
✔ Increment/Decrement with step value
✔ Save history
✔ Delete history items
✔ Fresh history on launch
✔ Fully commented Kotlin code
✔ GitHub project submission
