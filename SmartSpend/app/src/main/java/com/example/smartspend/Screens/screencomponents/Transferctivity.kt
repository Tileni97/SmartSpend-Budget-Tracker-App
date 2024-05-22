package com.example.smartspend.Screens.screencomponents

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.smartspend.data.UserRepository
import com.example.smartspend.navigation.Routes
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun TransferScreen(navController: NavHostController){



    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,

    ){
        NavBar(navController )

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
            Spacer(modifier = Modifier.size(10.dp))
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(color = Color(0xff009177))
                    .padding(10.dp, 0.dp)
                    .clickable {
                                navController.navigate(Routes.Extransfere.routes)
                    },
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
                    .clickable {
                                navController.navigate(Routes.Intransfere.routes)
                    },
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
        }
    }
}

@Composable
fun NavBar(navController: NavHostController){
    
    // Mutable state variables for each fields
    var userBalance by remember { mutableStateOf("") }

    //Fetch user email from repository
    var userEmail: String = UserRepository.getEmail()

    // Initialize Firebase Firestore
    val db = FirebaseFirestore.getInstance()

    // Fetch balance
    val userDocRef = db.collection("Users").document(userEmail)
    userDocRef.get().addOnSuccessListener { document ->
        var balance = document.data?.get("balance") as? String
        userBalance = balance.toString() // Update userBalance with the fetched balance
    }

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
                    .padding(5.dp)
                    .clickable {navController.popBackStack()}

            ){
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.White)
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
                    .clickable {navController.navigate(Routes.HelpScreen.routes)}

            ){
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "",tint = Color.White)
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
            Text(text = "N$ $userBalance", fontSize = 40.sp, fontWeight = FontWeight.W700,color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransferScreenPreview3() {
    val navControllerOne = rememberNavController()
    TransferScreen(navControllerOne)

}