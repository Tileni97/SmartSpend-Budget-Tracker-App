package com.example.smartspend.Screens.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme
import com.example.smartspend.navigation.Routes

@Composable
fun DashBordActivity(){
    Column (
        modifier = Modifier
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.size(15.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Bank Balance")
            Text(text = "15 000",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold
                )
        }
        DashTopBar()
    }
}

@Composable
fun DashTopBar(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(size = 10.dp)
                )
                .fillMaxWidth()
                .background(
                    color = Color(250,191,0)
                )
                .padding(10.dp)
        ){
            Column {
                Row {
                    Text(text = "Giideon s v")
                }
                Text(text = "XXXX XXXX XXXX XXXX 5446")
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Box (
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(size = 10.dp)
                            )
                            .background(
                                color = Color.Blue
                            )
                            .padding(10.dp)

                    ){
                        Column {
                            Text(text = "BUDGET")
                            Text(text = "N$ 500")
                        }
                    }

                    Spacer(modifier = Modifier.size(50.dp))

                    Box (
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(size = 10.dp)
                            )
                            .background(
                                color = Color.Blue
                            )
                            .padding(10.dp)
                            .width(150.dp)

                    ){
                        Column {
                            Text(text = "SPEND")
                            Text(text = "N$ 150")
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashBordActivityPreview() {
    SmartSpendTheme {
        DashBordActivity()
    }
}