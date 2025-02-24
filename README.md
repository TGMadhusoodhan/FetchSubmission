# FetchCoding App Documentation
## Overview
The FetchCoding app is an android application that retrives a list of items from the website directly using Retrofit: A type-safe HTTP client and displays them in a RecyclerView. These items can be sorted in ascending as well in descending order based on the listId.
This app also provides with alternating row colors for a clean UI and better readablity 
> [!NOTE]
> This is app is a submission for a app-coding round posted by ***Fetch***.
## Features
* Displays a list of items retrived from  <a> https://fetch-hiring.s3.amazonaws.com/hiring.json </a>
* Displays all the items grouped by "listId"
* Sort the results by "listId" and then by name while displaying
* Filters out any items that has no name or if it is NULL
* Provides a sort button to toggle between ascending and descending order
* Recycler view with alternating row colors for better readability and UI
##Installation & Setup
1. clone the repository using this command
   ```
   git clone https://github.com/TGMadhusoodhan/FetchSubmission.git
   ```
2. Extract and open the project in Android Studio
3. Build and run the ampplication using an emulator or a physical device
> [!TIP]
> You can also download the app directly just by installing fetchAPK.apk on ur emulator or your physical device directly.
##Code Structure
It contains 3 major files
1. FetchViewModel.kt
    - Fetches data directly using ***Retrofit API***
    - Stores the data retrived in a live data and sends it to the MainActivity
2. MainActivity.kt
    - Retrives livedata from FetchViewModel.kt
    - Contains RecyclerView adapter
    - Provides a cleaner UI
    - Contains a sort button to sort it in ascending and descending order
    - Provides different color for alternating rows for better readablity
3. SplashScreen.kt
    - Provides a SplashScreen for 2 sec before the app starts.
    - improves presentation 
