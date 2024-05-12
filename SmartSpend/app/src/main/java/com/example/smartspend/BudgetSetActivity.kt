package com.example.smartspend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.rounded.AddCard
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.Screens.home.HomeActivity
import com.example.smartspend.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class BudgetSetActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartSpendTheme {
                SetBarColor(color = Color(0xff009177))
                val intent = Intent(this, HomeActivity::class.java)

                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {

                        var transport by remember { mutableStateOf("") }
                        var food by remember { mutableStateOf("") }
                        var health by remember { mutableStateOf("") }
                        var education by remember { mutableStateOf("") }
                        var acommodation by remember { mutableStateOf("") }

                        var total:String ="0"

                        Text(
                            text = "Hi There !!!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xff009177),
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Let's start with setting up your budget.",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Center,
                            color = Color(0xff009177)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "TOTAL BUDGET",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.W700,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "N$ $total",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.W700,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Column (
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Column (
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                                ,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ){
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp, 0.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Icon(imageVector = Icons.Outlined.DirectionsCar, contentDescription = "", tint = Color(0xff009177))
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "TRANSPORT", textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                                    Box(
                                        modifier = Modifier
                                            .width(150.dp)
                                    ){
                                        TextField(value = transport, onValueChange = {transport=it},
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = Color.Transparent),
                                            placeholder = {Text(text = "500")},
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                            leadingIcon = {
                                                Icon(
                                                    imageVector = Icons.Outlined.AttachMoney,
                                                    contentDescription = "", tint = Color(0xff009177)
                                                )
                                            }
                                        )
                                    }

                                }
                                Spacer(modifier = Modifier.height(15.dp))
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp, 0.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Icon(imageVector = Icons.Outlined.Fastfood, contentDescription = "", tint = Color(0xff009177))
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "FOOD", textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                                    Box(
                                        modifier = Modifier
                                            .width(150.dp)
                                    ){
                                        TextField(value = food, onValueChange = {food=it},
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = Color.Transparent),
                                            placeholder = {Text(text = "1500")},
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                            leadingIcon = {
                                                Icon(
                                                    imageVector = Icons.Outlined.AttachMoney,
                                                    contentDescription = "", tint = Color(0xff009177)
                                                )
                                            }
                                        )
                                    }

                                }
                                Spacer(modifier = Modifier.height(15.dp))
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp, 0.dp)
                                    ,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Icon(imageVector = Icons.Outlined.LocalHospital, contentDescription = "", tint = Color.Red)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "HEALTH" , textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                                    Box(
                                        modifier = Modifier
                                            .width(150.dp)
                                    ){
                                        TextField(value = health, onValueChange = {health=it},
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = Color.Transparent),
                                            placeholder = {Text(text = "200")},
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                            leadingIcon = {
                                                Icon(
                                                    imageVector = Icons.Outlined.AttachMoney,
                                                    contentDescription = "", tint = Color(0xff009177)
                                                )
                                            }
                                        )
                                    }

                                }
                                Spacer(modifier = Modifier.height(15.dp))
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp, 0.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Icon(imageVector = Icons.Outlined.School, contentDescription = "", tint = Color.Green)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "EDUCATION", textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                                    Box(
                                        modifier = Modifier
                                            .width(150.dp)
                                    ){
                                        TextField(value = education, onValueChange = {education=it},
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = Color.Transparent),
                                            placeholder = {Text(text = "500")},
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                            leadingIcon = {
                                                Icon(
                                                    imageVector = Icons.Outlined.AttachMoney,
                                                    contentDescription = "", tint = Color(0xff009177)
                                                )
                                            }
                                        )
                                    }

                                }
                                Spacer(modifier = Modifier.height(15.dp))
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp, 0.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ){
                                    Icon(imageVector = Icons.Outlined.Hotel, contentDescription = "", tint = Color(0xff009177))
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(text = "ACOMODATION", textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                                    Box(
                                        modifier = Modifier
                                            .width(150.dp)
                                    ){
                                        TextField(value = acommodation, onValueChange = {acommodation=it},
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = Color.Transparent),
                                            placeholder = {Text(text = "500")},
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                            leadingIcon = {
                                                Icon(
                                                    imageVector = Icons.Outlined.AttachMoney,
                                                    contentDescription = "", tint = Color(0xff009177)
                                                )
                                            }
                                        )
                                    }

                                }
                                Spacer(modifier = Modifier.height(15.dp))
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Button(onClick = { startActivity(intent)},
                                modifier = Modifier
                                    .clip(RoundedCornerShape(15.dp))
                                    .width(200.dp)
                                    .background(color = Color(0xff009177)),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xff009177),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = "Finish", fontWeight = FontWeight.W700)

                            }
                        }
                    }
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
