package com.example.smartspend.Screens.screencomponents

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PhoneIphone
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.LandingActivity
import com.example.smartspend.Screens.screencomponents.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun TransferScreen(){
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,

    ){
        NavBar()

         Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),){

            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = "Transfer to:",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W700,
                fontSize = 19.sp
                )

            Spacer(modifier = Modifier.size(10.dp))
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color(0xff009177))
                    .padding(10.dp, 0.dp)
                    .clickable {},
                contentAlignment = androidx.compose.ui.Alignment.Center

            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "", tint = Color.Yellow
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "Favourite", fontFamily = FontFamily.SansSerif,
                        color = Color.Yellow, fontSize = 20.sp,
                        fontWeight = FontWeight.W700
                    )
                }

            }

            Spacer(modifier = Modifier.size(10.dp))
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color(0xff009177))
                    .padding(10.dp, 0.dp)
                    .clickable {},
                contentAlignment = androidx.compose.ui.Alignment.Center

            ){
                Row (
                    modifier = Modifier
                       .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Outlined.AccountBalance,
                        contentDescription = "", tint = Color.White
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "External Account", fontFamily = FontFamily.SansSerif,
                        color = Color.White, fontSize = 20.sp
                    )
                }

            }

            Spacer(modifier = Modifier.size(10.dp))
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color(0xff009177))
                    .padding(10.dp, 0.dp)
                    .clickable {},
                contentAlignment = androidx.compose.ui.Alignment.Center

            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Outlined.AccountBalance,
                        contentDescription = "", tint = Color.White
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "Internal Account", fontFamily = FontFamily.SansSerif,
                        color = Color.White, fontSize = 20.sp
                    )
                }

            }


            Spacer(modifier = Modifier.size(10.dp))
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color(0xff009177))
                    .padding(10.dp, 0.dp)
                    .clickable {},
                contentAlignment = androidx.compose.ui.Alignment.Center

            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Outlined.Savings,
                        contentDescription = "", tint = Color.White
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "Savings", fontFamily = FontFamily.SansSerif,
                        color = Color.White, fontSize = 20.sp
                    )
                }

            }

            Spacer(modifier = Modifier.size(10.dp))
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color(0xff009177))
                    .padding(10.dp, 0.dp)
                    .clickable {},
                contentAlignment = androidx.compose.ui.Alignment.Center

            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ){
                    Icon(imageVector = Icons.Outlined.PhoneIphone,
                        contentDescription = "", tint = Color.White
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "Mobile Account", fontFamily = FontFamily.SansSerif,
                        color = Color.White, fontSize = 20.sp
                    )
                }

            }



        }
    }
}

@Composable
fun NavBar(){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff009177)),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ){
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .clickable {}

            ){
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "",)
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ){
                Text(text = "Transfer",
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
                    .clickable {}

            ){
                Icon(imageVector = Icons.Outlined.HelpOutline, contentDescription = "",tint = Color.White)
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        ){
            Icon(imageVector = Icons.Outlined.AccountBalance, contentDescription = "",tint = Color.White)
            Text(text = "Bank Account Balance", fontFamily = FontFamily.SansSerif,
                color = Color.White
            )
            Text(text = "15000", fontSize = 40.sp, fontWeight = FontWeight.W700,color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransferScreenPreview3() {

    TransferScreen()

}