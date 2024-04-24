package com.example.smartspend.Screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.data.BottomNavItem
import com.example.smartspend.navigation.Routes

@Composable
fun HomeScreen(navController: NavController){
    val navControllerOne = rememberNavController()

    Scaffold (bottomBar = { MyBottomBar(navControllerOne)}){
            innerPadding ->
        NavHost(navController = navControllerOne, startDestination = Routes.Dashbord.routes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Routes.Dashbord.routes){
                DashBordActivity()
            }

            composable(Routes.Transactions.routes){
                TransactionScreen()
            }

            composable(Routes.Profile.routes){
                ProfileScreen()
            }
            composable(Routes.Setting.routes){
                SettingScreen()
            }
        }
    }
}

@Composable
fun MyBottomBar(navControllerOne: NavHostController){

    val backStackEntry = navControllerOne.currentBackStackEntryAsState()

    val list = listOf(

        BottomNavItem(
            title = "Home",
            Routes.Dashbord.routes,
            Icons.Rounded.Home
        ),

        BottomNavItem(
            title = "Transactions",
            Routes.Transactions.routes,
            Icons.Rounded.MonetizationOn
        ),

        BottomNavItem(title = "Profile",
            Routes.Profile.routes,
            Icons.Rounded.Person
        ),

        BottomNavItem(
            title = "Setting",
            Routes.Setting.routes,
            Icons.Rounded.Settings
        )
    )

    BottomAppBar {
        list.forEach{
            val selected: Boolean = it.route == backStackEntry?.value?.destination?.route

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navControllerOne.navigate(it.route) {
                        popUpTo(navControllerOne.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }

                },
                icon = {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Icon(imageVector = it.icon, contentDescription = it.title)
                    }
                }
            )
        }
    }
}