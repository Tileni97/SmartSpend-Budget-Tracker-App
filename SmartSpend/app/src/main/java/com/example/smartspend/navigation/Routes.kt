package com.example.smartspend.navigation

sealed class Routes (val routes:String){

    data object Dashbord : Routes("dashbord")
    data object Setting : Routes("setting")
    data object Profile : Routes("profile")
    data object Help : Routes("help")
    data object Splash : Routes("slash")
    data object BottomNav : Routes("bottomNav")
    data object Login : Routes("login")
    data object Register : Routes("register")
}