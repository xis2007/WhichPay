# WhichPay

An App that provides users informaon about the types of payment options merchants provide in Taiwan.
Users can easily find out where they can use mobile payment and what type of mobile payment options among the most popular ones, Apple Pay, Google Pay, Samsung Pay, Line Pay, and Jko Pay, they can use easily and quickly by searching in this App.

<br /><br />[<img src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" width="200">](https://play.google.com/store/apps/details?id=com.justinlee.whichpay)

# Features

  * Location Services
    * When user launches the App, the App will get user fine location by using GPS or Network
    * Users will be asked for their permissions during runtime to access their location information
    * If location services is not available, default locations will be used
    
  * Explore
    * At the Explore Page, users are provided with the latest information related to mobile payments with viewpager
    * Users can also search for nearby merchants by merchant types by pressing the shorcuts provided in the Explre Page, the results will be shown in recyclerview
    
  * Searching
    * Users can use the searching page to search for merchants and the result will be shown in recyclerview
    * Queries will be matched against merchant name or address
    * Results will be ordered in distance from merchant to user's current location in ascending order
    
  * Settings
    * User can set their default locations in the settings page in case user location is not available
    * User can also set the type of payment options they want to look for. For example, users may only want to look for merchants that allow Line Pay, after closing all other options, user search results will only have merchants they allows Line Pay.
    * User settings are stored with SharedPreferences

  * Google Maps
    * User can view search results in Google Maps, with their searched merchants shown in markers on the map
    * Clicking on each marker will show detail information about each merchant
  
# Screenshot

<img src="https://lh3.googleusercontent.com/shBiXk4icxuMAphOXCya3ZjC8c_XY60eQoygC2C-LiQURWmbB-5D0NGYbX2OgPZiZUM=w1440-h620-rw" width="210"> <img src="https://lh3.googleusercontent.com/4khaHdv_ZYRj8I0YsAfU2z3HMEazGLl1eyjNe2FD4i4DlXXrH2AYmipCCXEtmmyyvGN0=w1440-h620-rw" width="210"> <img src="https://lh3.googleusercontent.com/7OaQ0poOEFF62T6TrcM3cH9kZfR-lbHEmFiF996JeQP0ljWChhErQqCAVGudmujrCm0=w1440-h620-rw" width="210"> <img src="https://lh3.googleusercontent.com/fsNR8dlrBoIYQu5fqAdSXpqMAda15qOBVnZJtQXd1Bw6LTyEFCSQLwBBNmH-NSSLvw=w1440-h620-rw" width="210"> 

# Implemented
  (Some of the implementations will be available after refactoring of the App)
  
  * Design Patterns 
    * Object-Oreinted Programming
    * Model-View-Presenter (MVP) Model
    * Dependency Injection
    
  * Core Functions
    * Access User fine location with GPS or Network, using Service and BroadcastReceiver
    * Interfaces and Callbacks
    * Runtime Permissions for location services
    * Google Maps
    
  * User Interface
    * Activity
    * Fragment
    * RecyclerView 
    * Bottom Navigation
    * ViewPager
    * Dialog
    
  * Database
    * SharedPreferences
    * Firebase Firestore to store merchant and payment information

# Requirement
* Android Studio 3.0+
* Android SDK 21+
* Gradle 3.1.3+

# Version
* 1.2.15 - 2018/7/8
    Last Update

  

# Contact
justinlee.archer@gmail.com 
