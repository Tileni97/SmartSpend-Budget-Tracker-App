package com.example.smartspend.Screens.home

import android.widget.Toast
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
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.SyncAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.smartspend.data.Categories
import com.example.smartspend.data.CategoryRepository
import com.example.smartspend.data.UserRepository
import com.example.smartspend.data.dataBaseRepository
import com.example.smartspend.getCategory
import com.example.smartspend.navigation.Routes
import com.example.smartspend.setCategory
import kotlin.random.Random

// Main composable function for the Transaction screen
@Composable
fun TransactionScreen(navController: NavHostController) {
    val context = LocalContext.current
    val userEmail = UserRepository.getEmail()
    setCategory(userEmail, context)
    val category = getCategory(CategoryRepository.getAllCategories())


    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(0xff009177)
    ){


        // Column composable for the content below the image
        Column(
            modifier = Modifier
                .fillMaxSize() ,
            horizontalAlignment = Alignment.CenterHorizontally // Center the content horizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp)) // Vertical spacing

                Image(
                    modifier = Modifier
                        .requiredSize(290.dp, 180.dp)
                    , // Fill the maximum available size
                    painter = painterResource(id = R.drawable.transact), // Set the image resource
                    contentDescription = "happy woman", // Content description for accessibility
                    contentScale = ContentScale.FillBounds // Crop the image to fit the available space while maintaining aspect ratio
                )

            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = Color.White),
            ){

                Column {

                }
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

            Spacer(modifier = Modifier.height(16.dp)) // Vertical spacing
            // Subtitle text with styling
            Text(
                text = "Secure your financial future with our \ntrusted budgeting services",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.inverseOnSurface,
                modifier = Modifier
                    .padding(10.dp)
            )
            Spacer(modifier = Modifier.height(30.dp)) // Vertical spacing


            // Row of buttons with styling
            Row(modifier = Modifier
                .fillMaxWidth(),
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
            Spacer(modifier = Modifier.height(30.dp)) // Vertical spacing

            Column (
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(20.dp))
                Row (
                    modifier = Modifier.fillMaxWidth().padding(10.dp, 0.dp),
                    horizontalArrangement = Arrangement.Start
                ){
                    Box (modifier = Modifier.width(150.dp)){
                        Text(text = "Categories", fontWeight = FontWeight.Bold)
                    }
                    Box (modifier = Modifier.width(100.dp)){
                        Text(text = "Amount", fontWeight = FontWeight.Bold)
                    }
                    Box (modifier = Modifier.width(100.dp)){
                        Text(text = "Spent", fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))



                category.forEach {result ->
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(10.dp, 0.dp),
                        horizontalArrangement = Arrangement.Start
                    ){
                        Box (modifier = Modifier.width(150.dp)){
                            Text(text = "${result.name}", fontWeight = FontWeight.Bold, color = Color(0xff009177))
                        }
                        Box (modifier = Modifier.width(100.dp)){
                            Text(text = "N$ ${result.amount}", fontWeight = FontWeight.Bold)
                            Text(text = "N$", fontWeight = FontWeight.Bold, color = Color.Green)
                        }
                        Box {
                            Text(text = "N$ ${result.spend}", fontWeight = FontWeight.Bold)
                            Text(text = "N$", fontWeight = FontWeight.Bold, color = Color.Red)
                        }
                    }
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