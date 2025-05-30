package com.example.smartspend.Screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.data.NotificationRepository
import com.example.smartspend.data.UserRepository
import com.example.smartspend.getNotification
import com.example.smartspend.navigation.Routes
import com.example.smartspend.notificationRow
import com.example.smartspend.setNotification

@Composable
fun SettingScreen(navController: NavHostController){

    // Notificaions array list
   setNotification(UserRepository.getEmail())
    val notifications = getNotification(NotificationRepository.getAllNotification()).toMutableList()

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        NotificaionTopBar(navController)
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn (
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .fillMaxWidth()
                .heightIn(500.dp, 800.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(notifications) { notificationItem ->
                notificationRow(trans = notificationItem)
            }
        }
    }
}

@Composable
fun NotificaionTopBar(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff009177)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .padding(5.dp)
                    .clickable { navController.popBackStack() }

            ){
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.White)
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Notifications",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
            }
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    //.background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .clickable {navController.navigate(Routes.HelpScreen.routes)}

            ){
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "",tint = Color.White)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    val navControllerOne = rememberNavController()
    SettingScreen(navControllerOne)
}