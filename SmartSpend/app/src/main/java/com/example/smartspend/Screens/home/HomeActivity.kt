package com.example.smartspend.Screens.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Help
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme
import com.example.smartspend.data.BottomNavItem
import com.example.smartspend.navigation.Routes
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartSpendTheme {
                SetBarColor(color = Color(0xff009177))
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BottomNav(this)
                }
            }
        }
    }
    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }
}

@Composable
fun BottomNav(navController: NavController){
    val navControllerOne = rememberNavController()

    Scaffold (bottomBar = { MyBottomBar(navControllerOne)}){
            innerPadding ->
        NavHost(navController = navControllerOne, startDestination = Routes.Dashbord.routes,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Routes.Dashbord.routes){
                DashBordActivity()
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
            Routes.Home.routes,
            Icons.Rounded.Home
        ),

        BottomNavItem(title = "Help",
            Routes.Help.routes,
            Icons.Rounded.Help
        ),

        BottomNavItem(
            title = "Notifications",
            Routes.Notifications.routes,
            Icons.Rounded.Notifications
        ),

        BottomNavItem(
            title = "Profile",
            Routes.Profile.routes,
            Icons.Rounded.AccountCircle
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
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartSpendTheme {
    }
}