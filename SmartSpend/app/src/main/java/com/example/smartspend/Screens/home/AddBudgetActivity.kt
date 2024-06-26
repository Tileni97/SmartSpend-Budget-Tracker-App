package com.example.smartspend.Screens.home

import android.content.Context
import android.widget.Toast
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
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.LocalHospital
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.smartspend.data.UserRepository
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBudgetActivity(navController: NavHostController){

    var userEmail: String = UserRepository.getEmail()
    val context = LocalContext.current

    // Initialize Firebase Firestore
    val db = FirebaseFirestore.getInstance()

    // Mutable state variables for each field
    var transport by remember { mutableStateOf("") }
    var food by remember { mutableStateOf("") }
    var health by remember { mutableStateOf("") }
    var education by remember { mutableStateOf("") }
    var accommodation by remember { mutableStateOf("") }
    var budget by remember { mutableStateOf("") }

    // Derive the total budget amount from the text field values
    val totalBudget by remember {
        derivedStateOf {
            val transportValue = transport.toIntOrNull() ?: 0
            val foodValue = food.toIntOrNull() ?: 0
            val healthValue = health.toIntOrNull() ?: 0
            val educationValue = education.toIntOrNull() ?: 0
            val accommodationValue = accommodation.toIntOrNull() ?: 0

            transportValue + foodValue + healthValue + educationValue + accommodationValue
        }
    }

    budget = totalBudget.toString()


    val currentUser = UserRepository.getUsers()
    var userFirstName: String? = null
    

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


            //total =(transport.toInt()+food.toInt()+health.toInt()+education.toInt()+accommodation.toInt()).toString()

            Text(
                text = "Great work!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff009177),
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Now it's time to define your budget.",
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
                text = "N$ $totalBudget",
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
                        Text(text = "Transport", textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                        ){
                            TextField(value = transport, onValueChange = {transport=it},
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent),
                                placeholder = {Text(text = "0.00")},
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                leadingIcon = {
                                    Text(
                                        text = "N$",
                                        color = Color(0xff009177)
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
                        Text(text = "Food", textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                        ){
                            TextField(value = food, onValueChange = {food=it},
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent),
                                placeholder = {Text(text = "0.00")},
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                leadingIcon = {
                                    Text(
                                        text = "N$",
                                        color = Color(0xff009177)
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
                        Text(text = "Health" , textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                        ){
                            TextField(value = health, onValueChange = {health=it},
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent),
                                placeholder = {Text(text = "0.00")},
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                leadingIcon = {
                                    Text(
                                        text = "N$",
                                        color = Color(0xff009177)
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
                        Text(text = "Education", textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                        ){
                            TextField(value = education, onValueChange = {education=it},
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent),
                                placeholder = {Text(text = "0.00")},
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                leadingIcon = {
                                    Text(
                                        text = "N$",
                                        color = Color(0xff009177)
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
                        Text(text = "Accommodation", textAlign = TextAlign.Start, fontWeight = FontWeight.W700)
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                        ){
                            TextField(value = accommodation, onValueChange = {accommodation=it},
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.Transparent),
                                placeholder = {Text(text = "0.00")},
                                singleLine = true,
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                                leadingIcon = {
                                    Text(
                                        text = "N$",
                                        color = Color(0xff009177)
                                    )
                                }
                            )
                        }

                    }
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {

                    var isValid = true

                    if (transport.isBlank() || food.isBlank() || health.isBlank() || education.isBlank() || accommodation.isBlank()) {
                        isValid = false
                        showToast(context, "Please fill in all fields")
                    }

                    if (isValid){

                    // Update the data in Firestore
                        db.collection("Categories").document(userEmail)
                            .collection("budget").document("transport")
                            .update(
                                mapOf(
                                    "budget" to transport,
                                )
                            )

                        db.collection("Users").document(userEmail)
                            .update(
                             mapOf(
                                "budget" to budget
                            )
                        )

                        .addOnSuccessListener {
                            showToast(context, "Account information updated successfully")
                            navController.popBackStack()

                        }
                        .addOnFailureListener { exception ->
                            showToast(
                                context,
                                "Error updating account information: ${exception.message}"
                            )
                        }

                        db.collection("Categories").document(userEmail)
                            .collection("budget").document("food")
                            .update(
                                mapOf(
                                    "budget" to food,
                                )
                            )

                        db.collection("Categories").document(userEmail)
                            .collection("budget").document("health")
                            .update(
                                mapOf(
                                    "budget" to health,
                                    )
                            )

                        db.collection("Categories").document(userEmail)
                                .collection("budget").document("education")
                            .update(
                                mapOf(
                                    "budget" to education,
                                )
                            )


                        db.collection("Categories").document(userEmail)
                                .collection("budget").document("accomodation")
                            .update(
                                mapOf(
                                    "budget" to accommodation,
                                )
                            )
                    }
                },
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



private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}