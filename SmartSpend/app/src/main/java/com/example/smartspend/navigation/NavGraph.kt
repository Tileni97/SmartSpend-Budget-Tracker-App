package com.example.smartspend.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.Screens.home.DashBordActivity
import com.example.smartspend.Screens.home.HomeScreen
import com.example.smartspend.Screens.home.ProfileScreen
import com.example.smartspend.Screens.home.SettingScreen
import com.example.smartspend.Screens.home.TransactionScreen
import com.example.smartspend.Screens.screencomponents.PayScreen
import com.example.smartspend.Screens.screencomponents.TransferScreen

@Composable
fun NavGraph(navController: NavHostController){

    NavHost(navController = navController, startDestination = Routes.HomeScreen.routes) {

        composable(Routes.HomeScreen.routes){
            HomeScreen(navController)
        }
        composable(Routes.Profile.routes){
            ProfileScreen()
        }
        composable(Routes.Setting.routes){
            SettingScreen()
        }
        composable(Routes.Dashbord.routes){
            DashBordActivity(navController)
        }
        composable(Routes.Transactions.routes){
            TransferScreen()
        }
        composable(Routes.PayScreen.routes){
            PayScreen()
        }

    }

}