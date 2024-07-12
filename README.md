# Smart Spend: Your Intelligent Budgeting Companion

[![Image of Smart Spend Logo or App Icon]](images/app_icon.png) 

Developed by:

* Gideon S.V ([[https://github.com/Shikongo19](https://github.com/Shikongo19)]([https://github.com/Shikongo19](https://github.com/Shikongo19)))
* Tangi H.S Petrus ([[https://github.com/Supertangi](https://github.com/Supertangi)]([https://github.com/Supertangi](https://github.com/Supertangi)))
* C. T Hango (@Tileni)

## Overview

Smart Spend is a mobile budgeting application that empowers users to take control of their finances. It seamlessly integrates with bank accounts, automatically tracks and categorizes transactions, and offers personalized budgeting features and actionable insights. Smart Spend aims to promote financial literacy, encourage responsible spending, and help users achieve their financial goals while avoiding debt.

## Key Features

* **Real-time Transaction Tracking:** Automatically imports and categorizes transactions from linked bank accounts.
* **Personalized Budgeting:** Create custom budgets for different spending categories.
* **Actionable Insights:** Gain insights into spending patterns with clear visualizations.
* **Customizable Alerts:** Receive notifications for nearing budget limits or unusual spending.
* **Secure and Private:**  Employs industry-standard security practices to protect user data.

## Objective

The primary objective of Smart Spend is to develop a user-friendly mobile application that:

* Empowers individuals to make informed financial decisions.
* Promotes responsible spending habits.
* Helps users achieve their financial goals and avoid debt.
* Enhances financial literacy through intuitive tools and insights.


## System-Level Design

* **Mobile Application (Android)**
    * UI built with Jetpack Compose for modern design and intuitive user experience.
    * Employs Retrofit for seamless integration with bank APIs.
    * Uses Room Persistence Library for potential local data caching.
* **Backend (Firebase)**
    * Firebase Firestore as the NoSQL database for user data storage.
    * Firebase Authentication for secure user login and management.
    * Firebase Cloud Functions for serverless backend logic.

## Functional Requirements

* **User Management:**
    * Account creation and login
    * Profile management
* **Account Integration:**
    * Secure connection to financial institutions via APIs
    * Account aggregation and transaction retrieval
* **Transaction Management:**
    * Automatic and manual categorization
    * Transaction search and filtering
* **Transaction Tracking and Insights:**
    * Income and expense tracking
    * Budget breakdowns
    * Transaction history
    * Spending visualizations 
* **Notifications and Alerts:**
    * Customizable budget alerts and reminders
* **Transfers:**
    * Transfer funds between internal accounts.

## Non-Functional Requirements

* **Performance:**  Responsive mobile app and efficient backend.
* **Scalability:** Designed to handle a growing user base with Firebase's inherent scalability.
* **Security:**  Prioritizes data security with encryption and best practices.
* **Availability:**  Aims for high availability for continuous user access.
* **Usability:** Intuitive interface and clear navigation.

## Hardware/Software Specifications

* **Minimum Requirements (Android):**
    * Android 8.0 (Oreo) or higher
    * 2GB RAM, 100MB storage
* **Recommended Specifications:**
    * Android 10 (Q) or higher
    * 4GB RAM, 250MB storage
    * Octa-core processor (1.8GHz or higher)
* **Development Environment:**
    * Android Studio 
    * Kotlin programming language
    * Jetpack Compose UI framework
    * Libraries: Retrofit, Room, Firebase 

## Future Enhancements

* Debt management tools
* Investment tracking and insights
* Financial education resources
* Integration with additional financial services 

## Screenshots

![App Dashboard](SS%20app%20Images/Screenshot_20240519-211015.png)

![Budget Creation Screen](SS%20app%20Images/Screenshot_20240519-211027.png)

![Profile Update Screen](SS%20app%20Images/Screenshot_20240519-211043.png)

![Internal Transfer Screen](SS%20app%20Images/Screenshot_20240519-211606.png)

![Screenshot 5](SS%20app%20Images/Screenshot_20240519-211621.png)

![Screenshot 6](SS%20app%20Images/Screenshot_20240519-211639.png)

![Screenshot 7](SS%20app%20Images/Screenshot_20240519-211707.png)

![Screenshot 8](SS%20app%20Images/Screenshot_20240519-211715.png)

![Screenshot 9](SS%20app%20Images/Screenshot_20240519-211727.png)

![Screenshot 10](SS%20app%20Images/Screenshot_20240519-211734.png)

![Screenshot 11](SS%20app%20Images/Screenshot_20240519-211818.png)



## License

This project is licensed under the MIT License.

## Getting Started

* Clone the repository.
* Set up your Firebase project and link it to the app.
* Build and run the app on your Android device or emulator.

## Contributing

We welcome contributions!  
