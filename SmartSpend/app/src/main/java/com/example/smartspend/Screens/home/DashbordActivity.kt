package com.example.smartspend.Screens.home

import android.content.ContentValues.TAG
import android.util.Log
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowOutward
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SyncAlt
import androidx.compose.material.icons.outlined.TransitEnterexit
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.R
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme
import com.example.smartspend.data.TransectionItem
import com.example.smartspend.data.TransectionRepository
import com.example.smartspend.data.UserData
import com.example.smartspend.data.UserRepository
import com.example.smartspend.data.dataBaseRepository
import com.example.smartspend.navigation.Routes
import com.example.smartspend.setTransection
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@Composable
fun DashBordActivity(navController: NavHostController) {
    var user: UserData = UserData(username = "shikongov02@gmail.com", firstName = "Shikongo", lastName = "Giideon", phone = "+264814272721", accountType = "standard", address = "Tuba Street", balance = 20000, budget = 1500, spent = 1100, cardNumber = "5343875934363775", expMonth = 4, expYear = 24, cvv = 254, AccountNumber = "2424789349735768")

    var userEmail: String = UserRepository.getEmail()

    setTransection(userEmail)

    // Create a mutable state variable to store the lastName
    var userFirstName by remember { mutableStateOf("") }
    var userLastName by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }
    var userAccountType by remember { mutableStateOf("") }
    var userAddress by remember { mutableStateOf("") }
    var userBalance by remember { mutableStateOf("") }
    var userSpent by remember { mutableStateOf(0) }
    var userBudget by remember { mutableStateOf(0) }
    var userCardNumber by remember { mutableStateOf("") }
    var userExpMonth by remember { mutableStateOf(0) }
    var userExpYear by remember { mutableStateOf(0) }
    var userCvv by remember { mutableStateOf(0) }




    FirebaseFetch()
    val db = dataBaseRepository.getDb()
    val userDocRef = db.collection("Users").document(userEmail)


    val document = userDocRef.get().addOnSuccessListener { document ->
        var username = document.data?.get("username") as? String
        var firstName = document.data?.get("firstName") as? String
        var lastName = (document.data?.get("lastName") as? String)
        var phone = document.data?.get("phone") as? String
        var accountType = document.data?.get("accountType") as? String
        var address = document.data?.get("address") as? String
        var balance = document.data?.get("balance") as? String
        var spent = document.data?.get("spent") as? String
        var budget = document.data?.get("budget") as? String
        var cardNumber = document.data?.get("cardNumber") as? String
        var expMonth = document.data?.get("expMonth") as? String
        var expYear = document.data?.get("expYear") as? String
        var cvv = document.data?.get("cvv") as? String



        userFirstName = firstName.toString()
        userLastName = lastName.toString()
        userPhone = phone.toString()
        userAccountType = accountType.toString()
        userAddress = address.toString()
        if (balance != null) {
            userBalance = balance.toString()
        }
        if (spent != null) {
            userSpent = spent.toInt()
        }
        if (budget != null) {
            userBudget = budget.toInt()
        }
        userCardNumber = cardNumber.toString()
        if (expMonth != null) {
            userExpMonth = expMonth.toInt()
        }
        if (expYear != null) {
            userExpYear = expYear.toInt()
        }
        if (cvv != null) {
            userCvv = cvv.toInt()
        }
    }

    var translist = TransectionRepository.getTransection()

    var spending:Int =((userSpent.toDouble()/userBudget.toDouble())*100).toInt()

    if(spending <=50){
        Box(modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 100.dp))
            .fillMaxWidth()
            .height(270.dp)
            .background(color = Color(0xff009177)))
    }
    else if(spending >50 && spending <=75){
        Box(modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 100.dp))
            .fillMaxWidth()
            .height(270.dp)
            .background(color = Color(104, 115, 8))
        )
    }
    else {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 100.dp))
            .fillMaxWidth()
            .height(270.dp)
            .background(color = Color.Red)
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(15.dp))
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box (
                    modifier = Modifier
                        .clip(RoundedCornerShape(size = 20.dp))
                        .padding(10.dp)
                        .clickable {navController.navigate(Routes.Profile.routes)}
                ){
                    Icon(imageVector = Icons.Rounded.Person, contentDescription = "", tint = Color.White)
                }

                Text(
                    text = "Account Balance",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif
                )
                Box (
                    modifier = Modifier
                        .clip(RoundedCornerShape(size = 20.dp))
                        .padding(10.dp)
                        .clickable { }
                ){
                    Icon(imageVector = Icons.Rounded.Logout, contentDescription = "", tint = Color.White)
                }
            }

            Text(
                text = "N$ $userBalance ",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        DashTopBar(user)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceAround) {
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
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .width(90.dp)
                    .clickable { navController.navigate(Routes.PayScreen.routes) }
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
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                    .padding(5.dp)
                    .width(90.dp)
                    .clickable { }
                    .shadow(
                        elevation = 10.dp,
                        spotColor = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(1.dp),
                        ambientColor = MaterialTheme.colorScheme.onBackground
                    )
            ){
                Row {
                    Icon(imageVector = Icons.Outlined.Settings, contentDescription = "")
                    Text(text = "Manage",
                        fontWeight = FontWeight.W400,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 15.sp,fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(25.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Text(text = "Latest Transactions",
                fontFamily = FontFamily.SansSerif,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )


        }

        LazyColumn (
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .fillMaxWidth()
                .heightIn(500.dp, 800.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(translist.toList()) { transItem ->
                TransectionRow(trans = transItem)
            }
        }
    }
}


@Composable
fun DashTopBar(user: UserData){

    // Create a mutable state variable to store the lastName
    var userFirstName by remember { mutableStateOf("") }
    var userLastName by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }
    var userAccountType by remember { mutableStateOf("") }
    var userAddress by remember { mutableStateOf("") }
    var userBalance by remember { mutableStateOf("") }
    var userSpent by remember { mutableStateOf(0) }
    var userBudget by remember { mutableStateOf(0) }
    var userCardNumber by remember { mutableStateOf("") }
    var userExpMonth by remember { mutableStateOf(0) }
    var userExpYear by remember { mutableStateOf(0) }
    var userCvv by remember { mutableStateOf(0) }

    var userEmail: String = UserRepository.getEmail()


    FirebaseFetch()
    val db = FirebaseFirestore.getInstance()
    val userDocRef = db.collection("Users").document(userEmail)
    val document = userDocRef.get().addOnSuccessListener { document ->
        var username = document.data?.get("username") as? String
        var firstName = document.data?.get("firstName") as? String
        var lastName = (document.data?.get("lastName") as? String)
        var phone = document.data?.get("phone") as? String
        var accountType = document.data?.get("accountType") as? String
        var address = document.data?.get("address") as? String
        var balance = document.data?.get("balance") as? String
        var spent = document.data?.get("spent") as? String
        var budget = document.data?.get("budget") as? String
        var cardNumber = document.data?.get("cardNumber") as? String
        var expMonth = document.data?.get("expMonth") as? String
        var expYear = document.data?.get("expYear") as? String
        var cvv = document.data?.get("cvv") as? String

        userFirstName = firstName.toString()
        userLastName = lastName.toString()
        userPhone = phone.toString()
        userAccountType = accountType.toString()
        userAddress = address.toString()
        if (balance != null) {
            userBalance = balance.toString()
        }
        if (spent != null) {
            userSpent = spent.toInt()
        }
        if (budget != null) {
            userBudget = budget.toInt()
        }
        userCardNumber = cardNumber.toString()

        if (expMonth != null) {
            userExpMonth = expMonth.toInt()
        }
        if (expYear != null) {
            userExpYear = expYear.toInt()
        }
        if (cvv != null) {
            userCvv = cvv.toInt()
        }
    }


    Column (
        modifier = Modifier

            .fillMaxWidth()

            ,

        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box (
            modifier = Modifier
                .clip(RoundedCornerShape(size = 10.dp))

                .padding(30.dp)
                .shadow(
                    elevation = 10.dp,
                    spotColor = MaterialTheme.colorScheme.onBackground,
                    shape = RoundedCornerShape(1.dp),
                    ambientColor = MaterialTheme.colorScheme.onBackground
                )
        ){
            if(user.accountType.equals("standard")){
                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(size = 10.dp)
                        )
                        .fillMaxWidth()
                        .background(
                            color = Color.Black
                        )
                        .padding(10.dp)
                ){
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {

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
                        var size: Any = user.cardNumber?.length ?:
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(userCardNumber,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color =Color(255, 191, 0)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ){
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .width(90.dp)
                                    .background(
                                        color = Color(11, 158, 55)
                                    )
                                    .padding(5.dp, 0.dp),


                                ){
                                Column {
                                    Text(text = "BUDGET",
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                    Text(text = "N$" + (userBudget).toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(2.dp))
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .width(90.dp)
                                    .background(
                                        color = Color(122, 24, 5)
                                    )
                                    .padding(5.dp, 0.dp)

                            ){
                                Column {
                                    Text(text = "SPENT",
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                    Text(text = "N$" + (userSpent).toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(2.dp))
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .width(90.dp)
                                    .background(
                                        color = Color(102, 98, 15)
                                    )
                                    .padding(5.dp, 0.dp)

                            ){
                                Column {
                                    Text(text = "REMAIN",
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                    Text(text = "N$" + (userBudget - userSpent).toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = "0" + userExpMonth + "/" + userExpYear,color =Color.White)
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = "$userFirstName $userLastName",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400,
                                color = Color.White
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_visa),
                                contentDescription = "Card 18",
                                modifier = Modifier
                                    .requiredWidth(60.dp)
                            )
                        }
                    }

                }
            }
            else if(user.accountType.equals("VIP")){
                /*Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(size = 10.dp)
                        )
                        .fillMaxWidth()
                        .background(
                            color = Color.Black
                        )
                        .padding(10.dp)
                ){
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {

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
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = user.cardNumber,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color =Color(255, 191, 0)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ){
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .width(90.dp)
                                    .background(
                                        color = Color(11, 158, 55)
                                    )
                                    .padding(5.dp, 0.dp),


                                ){
                                Column {
                                    Text(text = "BUDGET",
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                    Text(text = "N$ 380",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(2.dp))
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .width(90.dp)
                                    .background(
                                        color = Color(122, 24, 5)
                                    )
                                    .padding(5.dp, 0.dp)

                            ){
                                Column {
                                    Text(text = "SPEND",
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                    Text(text = "N$ 380",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(2.dp))
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .width(90.dp)
                                    .background(
                                        color = Color(102, 98, 15)
                                    )
                                    .padding(5.dp, 0.dp)

                            ){
                                Column {
                                    Text(text = "REMAIN",
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                    Text(text = "N$ 380",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = "03/27",color =Color.White)
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = user.firstName[0] + " " + user.lastName,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400,
                                color = Color.White
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_visa),
                                contentDescription = "Card 18",
                                modifier = Modifier
                                    .requiredWidth(60.dp)
                            )
                        }
                    }
                }*/
            }
            else {
                /*Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(size = 10.dp)
                        )
                        .fillMaxWidth()
                        .background(
                            color = Color.Black
                        )
                        .padding(10.dp)
                ){
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {

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
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = "XXXX XXXX XXXX XXXX 5446",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color =Color(255, 191, 0)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ){
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .width(90.dp)
                                    .background(
                                        color = Color(11, 158, 55)
                                    )
                                    .padding(5.dp, 0.dp),


                                ){
                                Column {
                                    Text(text = "BUDGET",
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                    Text(text = "N$ 380",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(2.dp))
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .width(90.dp)
                                    .background(
                                        color = Color(122, 24, 5)
                                    )
                                    .padding(5.dp, 0.dp)

                            ){
                                Column {
                                    Text(text = "SPEND",
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                    Text(text = "N$ 380",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.size(2.dp))
                            Box (
                                modifier = Modifier
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .width(90.dp)
                                    .background(
                                        color = Color(102, 98, 15)
                                    )
                                    .padding(5.dp, 0.dp)

                            ){
                                Column {
                                    Text(text = "REMAIN",
                                        fontWeight = FontWeight.W400,
                                        color = Color.White
                                    )
                                    Text(text = "N$ 380",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 15.sp
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = "03/27",color =Color.White)
                        }
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(text = user.firstName[0] + " " + user.lastName,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W400,
                                color = Color.White
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_visa),
                                contentDescription = "Card 18",
                                modifier = Modifier
                                    .requiredWidth(60.dp)
                            )
                        }
                    }
                }*/
            }

        }
    }
}

@Composable
fun TransectionRow(
    modifier: Modifier = Modifier,
    trans: TransectionItem,){
    Surface(
        modifier
            .padding(15.dp, 3.dp)
            .clip(shape = RoundedCornerShape(size = 10.dp))
            //
            // to clip using a shape
            //.width(330.dp)
            .clickable { }
            ,
        //color = Color(18, 148, 139)
    ) {

        Column(
            modifier
                .padding(
                    10.dp, 2.dp
                )

                ,

            ) {

            if(trans.type =="income" || trans.type =="Income"){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row{
                        Icon(imageVector = Icons.Outlined.TransitEnterexit, contentDescription = "")
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = trans.category?:"No Category",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp
                        )
                    }
                    Row {
                        Icon(imageVector = Icons.Outlined.AttachMoney, contentDescription = "")
                        Spacer(modifier = Modifier.size(5.dp))
                        Text(text = trans.amount.toString()?:"NO Amount",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Green,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        Icon(imageVector = Icons.Outlined.ArrowDownward, contentDescription ="", tint = Color.Green )
                    }



                }

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(imageVector = Icons.Outlined.AccessTime, contentDescription ="" )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = trans.entryDate?: "",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.W400,
                        fontSize = 13.sp)

                }
            }
            else{
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row {
                        Icon(imageVector = Icons.Outlined.ArrowOutward, contentDescription = "")
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(text = trans.category?:"NO Category",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.W400,
                            fontSize = 15.sp
                        )
                    }

                    Row {
                        Icon(imageVector = Icons.Outlined.AttachMoney, contentDescription = "")
                        Spacer(modifier = Modifier.size(5.dp))
                        Text(text = trans.amount.toString()?:"NO Amount",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Red,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                        Icon(imageVector = Icons.Outlined.ArrowUpward, contentDescription ="", tint = Color.Red )
                    }

                }

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ){
                    Icon(imageVector = Icons.Outlined.AccessTime, contentDescription ="" )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(text = trans.entryDate?: "",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.W400,
                        fontSize = 13.sp)

                }
            }




            // and set it to this one
            /*Text(text = note.entryDate.time,
                style = MaterialTheme.typography.titleSmall)

             */



        }


    }

}

@Composable
fun FirebaseFetch() {
    var userEmail: String = UserRepository.getEmail()
    val context= LocalContext.current
    val db = Firebase.firestore
    val userDocRef = db.collection("Users").document(userEmail)


    userDocRef.get()
        .addOnSuccessListener { document ->

            if (document != null) {
                var username = document.data?.get("username") as? String
                var firstName = document.data?.get("firstName") as? String
                var lastName = document.data?.get("lastName") as? String
                var phone = document.data?.get("phone") as? String
                var accountType = document.data?.get("accountType") as? String
                var address = document.data?.get("address") as? String
                var balance = document.data?.get("balance") as? String
                var spent = document.data?.get("spent") as? String
                var budget = document.data?.get("budget") as? String
                var cardNumber = document.data?.get("cardNumber") as? String
                var expMonth = document.data?.get("expMonth") as? String
                var expYear = document.data?.get("expYear") as? String
                var cvv = document.data?.get("cvv") as? String


                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                //Toast.makeText(context, "$expYear", Toast.LENGTH_SHORT).show()
            } else {
                Log.d(TAG, "No such document")
            }

        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }

}

@Preview(showBackground = true)
@Composable
fun DashBordActivityPreview() {
    SmartSpendTheme {
        val navControllerOne = rememberNavController()
        DashBordActivity(navControllerOne)
    }
}