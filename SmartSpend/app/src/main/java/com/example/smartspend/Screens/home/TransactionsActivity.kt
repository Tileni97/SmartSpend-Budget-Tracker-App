package com.example.smartspend.Screens.home

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.SyncAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.R
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme
import com.example.smartspend.navigation.Routes

// Main composable function for the Transaction screen
@Composable
fun TransactionScreen(navController: NavHostController) {
    // Box composable with rounded corner shape, background color, and fixed height
    Box(
        modifier = Modifier
            .fillMaxWidth() // Fill the maximum available width
            .clip(RoundedCornerShape(bottomEnd = 100.dp)) // Apply rounded corner shape to the bottom-end corner
            .height(300.dp) // Set a fixed height of 400dp
            .background(color = Color(0xff009177)) // Set the background color to yellow
    ) {
        // Image composable inside the Box, filling the maximum size and cropping to fit
        Image(
            modifier = Modifier.fillMaxSize(), // Fill the maximum available size
            painter = painterResource(id = R.drawable.transact), // Set the image resource
            contentDescription = "happy woman", // Content description for accessibility
            contentScale = ContentScale.Crop // Crop the image to fit the available space while maintaining aspect ratio
        )
    }

    // Column composable for the content below the image
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the maximum available size
            .padding(horizontal = 16.dp), // Add horizontal padding
        //verticalArrangement = Arrangement.Center, // Center the content vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center the content horizontally
    ) {
        Spacer(modifier = Modifier.
                    size(200.dp)) // Add vertical spacing of 15dp

        // Title text with styling
        Box (
            modifier = Modifier
                .clip(RoundedCornerShape(size = 10.dp))
                .background(color =  Color.White),
        ){
            Text(
                text = "Manage your finances \nANYTIME, ANYWHERE",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(219, 132, 11)
                ),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(10.dp, 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(36.dp)) // Vertical spacing
        // Subtitle text with styling
        Text(
            text = "Secure your financial future with our \ntrusted budgeting services",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.inverseOnSurface
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.height(30.dp)) // Vertical spacing


        // Row of buttons with styling
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
            //Transfer button
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .width(90.dp)
                    .clickable {

                        navController.navigate(Routes.TransferScreen.routes)

                    }
                    .shadow(
                        elevation = 10.dp,
                        spotColor = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(1.dp),
                        ambientColor = MaterialTheme.colorScheme.onBackground
                    ),

                ){
                Row {
                    Icon(imageVector = Icons.Outlined.SyncAlt, contentDescription = "")
                    Text(text = "Transfer",
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 15.sp,fontFamily = FontFamily.SansSerif
                    )
                }
            }

            //Pay button
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .width(90.dp)
                    .clickable {

                        navController.navigate(Routes.PayScreen.routes)

                    }
                    .shadow(
                        elevation = 10.dp,
                        spotColor = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(1.dp),
                        ambientColor = MaterialTheme.colorScheme.onBackground
                    )
            ){
                Row {
                    Icon(imageVector = Icons.Outlined.AttachMoney, contentDescription = "")
                    Text(text = "Pay",
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 15.sp,fontFamily = FontFamily.SansSerif
                    )
                }
            }

            //Buy button
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .width(90.dp)
                    .clickable { navController.navigate(Routes.BuyScreen.routes) }
                    .shadow(
                        elevation = 10.dp,
                        spotColor = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(1.dp),
                        ambientColor = MaterialTheme.colorScheme.onBackground
                    )
            ){
                Row {
                    Icon(imageVector = Icons.Outlined.AccountBalanceWallet, contentDescription = "")
                    Text(text = "Buy",
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 15.sp,fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
    }
}

// Preview for Transaction screen
@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    val navController = rememberNavController()
    SmartSpendTheme {
        TransactionScreen(navController)
    }
}