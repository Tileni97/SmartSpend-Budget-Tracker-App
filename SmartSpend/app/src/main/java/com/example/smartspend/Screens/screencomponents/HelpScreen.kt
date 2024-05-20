package com.example.smartspend.Screens.screencomponents

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.R

@Composable
fun HelpScreen(navController: NavHostController){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        HelpNavBar(navController)
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(size = 100.dp))
                        .background(color = Color(0xff009177))
                        .padding(10.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "S",
                        color =Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Text(
                        text = "S",
                        color = Color.Green,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .scale(1f, -1f) // Inverted vertically
                            .rotate(degrees = 180f) // Rotated 180 degrees to correct orientation
                    )
                }
                Text(
                    text = "SmartSpend",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xff009177))
                Spacer(modifier = Modifier.height(20.dp))
            }

            Image(
                painter = painterResource(id = R.drawable.helping),
                contentDescription = "Card 18",
                modifier = Modifier
                    .requiredWidth(200.dp)
            )
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Service Numbers:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xff009177))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "+264 81 427 2721",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.SansSerif)
                Text(
                    text = "+264 81 740 0117",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.SansSerif)
                Text(
                    text = "+264 85 773 8151",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.SansSerif,)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "smartspend@gmail.com",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xff009177),
                    textDecoration = TextDecoration.Underline
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "App version 1.0.0",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.SansSerif,
                )
            }

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Developed by:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xff009177),
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Hango C",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xff009177),
                )
                Text(
                    text = "Giideon V",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xff009177),
                )
                Text(
                    text = "Petrus T",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.SansSerif,
                    color = Color(0xff009177),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "copyright © 2024",
                fontSize = 20.sp,
                fontWeight = FontWeight.W700,
                fontFamily = FontFamily.SansSerif,
            )
        }
    }
}


@Composable
fun HelpNavBar(navController: NavHostController){
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
                    .clickable {
                        navController.popBackStack()
                    }

            ){
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.White)
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "More About SmartSpent",
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

            ){
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HelpScreenPreview(){
    var navController = rememberNavController()
    HelpScreen(navController)
}