package com.example.smartspend.Screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DataExploration
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.Screens.screencomponents.ExtransfereScreen
import com.example.smartspend.Screens.screencomponents.IntransfereScreen
import com.example.smartspend.Screens.screencomponents.PayScreen
import com.example.smartspend.Screens.screencomponents.TransferScreen
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
                DashBordActivity(navControllerOne)
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
            composable(Routes.TransferScreen.routes){
                TransferScreen(navControllerOne)
            }
            composable(Routes.PayScreen.routes){
                PayScreen()
            }
            composable(Routes.Extransfere.routes){
                ExtransfereScreen(navControllerOne)
            }
            composable(Routes.Intransfere.routes){
                IntransfereScreen(navControllerOne)
            }
        }
    }
}

@Composable
fun MyBottomBar(navControllerOne: NavHostController) {
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
        BottomNavItem(
            title = "Analysis",
            Routes.Profile.routes,
            Icons.Rounded.DataExploration
        ),
        BottomNavItem(
            title = "Notification",
            Routes.Setting.routes,
            Icons.Rounded.Notifications
        )
    )


    Column {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {


            BottomAppBar {



                list.forEach { item ->
                    val selected: Boolean = item.route == backStackEntry?.value?.destination?.route

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navControllerOne.navigate(item.route) {
                                popUpTo(navControllerOne.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Icon(imageVector = item.icon, contentDescription = item.title)

                            }
                        }
                    )
                }


            }


            FloatingActionButton(
                onClick = { navControllerOne.navigate(Routes.TransferScreen.routes) {

                    popUpTo(navControllerOne.graph.findStartDestination().id){
                       saveState = true
                    }

                    launchSingleTop = true

                }
                },
                containerColor = Color(0xff009177),
                contentColor = Color.White,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.TopCenter)
                    .shadow(elevation = 50.dp, shape = CircleShape)
                    .padding(7.dp)
                ,

            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Budget"
                )
            }



        }


    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}