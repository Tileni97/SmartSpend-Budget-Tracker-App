package com.example.smartspend

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.rounded.AddCard
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.data.UserData
import com.example.smartspend.data.UserRepository
import com.example.smartspend.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.firestore.FirebaseFirestore

class AccountSetActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartSpendTheme {
                SetBarColor(color = Color(0xff009177))
                val intent = Intent(this, BudgetSetActivity::class.java)

                // mutable state variables for each field
                var accountNumber by remember { mutableStateOf("") }
                var cardNumber by remember { mutableStateOf("") }
                var expMonth by remember { mutableStateOf("") }
                var expYear by remember { mutableStateOf("") }
                var cvv by remember { mutableStateOf("") }

                val currentUser = UserRepository.getUsers()
                var userEmail: String = UserRepository.getEmail()
                var userFirstName: String? = null

                // Initialize Firebase Firestore
                val db = FirebaseFirestore.getInstance()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    //val userData = intent.getParcelableExtra<UserData>("USER_DATA")

                    Box (
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.background),
                        contentAlignment = Alignment.Center
                    ){
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {

                        currentUser.forEach {user: UserData ->
                            userFirstName = user.firstName
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "S",
                                color =Color.White,
                                fontSize = 100.sp,
                                fontWeight = FontWeight.ExtraBold,
                                
                            )
                            Text(
                                text = "S",
                                color = Color.Green,
                                fontSize = 100.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier
                                    .scale(1f, -1f) // Inverted vertically
                                    .rotate(degrees = 180f) // Rotated 180 degrees to correct orientation
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Hi $userFirstName!",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xff009177),
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Let's start setting up your account.",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W400,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(60.dp))
                        Column(
                            modifier = Modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(10.dp, 10.dp)
                                    .clip(RoundedCornerShape(size = 10.dp))
                                    .fillMaxWidth()
                                    .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                                ,
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                TextField(
                                    value = accountNumber, onValueChange = {accountNumber = it},
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.Transparent
                                    ),
                                    label = { Text(text = "Account Number") },
                                    placeholder = { Text(text = "4848653456755435") },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Outlined.Business,
                                            contentDescription = "", tint = Color(0xff009177)
                                        )
                                    },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                TextField(
                                    value = cardNumber, onValueChange = {cardNumber = it},
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.Transparent
                                    ),
                                    label = { Text(text = "Card Number") },
                                    placeholder = { Text(text = "64274623874246824") },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Rounded.AddCard,
                                            contentDescription = "", tint = Color.Green
                                        )
                                    },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .width(120.dp)
                                            .padding(5.dp, 0.dp),
                                    ) {
                                        TextField(
                                            value = expMonth, onValueChange = {expMonth = it},
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = Color.Transparent
                                            ),
                                            label = { Text(text = "Exp Mon", fontSize = 15.sp) },
                                            placeholder = { Text(text = "3") },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                        )
                                    }
                                    Box(
                                        modifier = Modifier
                                            .width(120.dp)
                                            .padding(5.dp, 0.dp),
                                    ) {
                                        TextField(
                                            value = expYear, onValueChange = {expYear = it},
                                            colors = TextFieldDefaults.textFieldColors(
                                                containerColor = Color.Transparent
                                            ),
                                            label = { Text(text = "Exp Year") },
                                            placeholder = { Text(text = "3") },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                        )
                                    }
                                    TextField(
                                        value = cvv, onValueChange = {cvv = it},
                                        colors = TextFieldDefaults.textFieldColors(
                                            containerColor = Color.Transparent
                                        ),
                                        label = { Text(text = "CVV") },
                                        placeholder = { Text(text = "472") },
                                        singleLine = true,
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                                    )
                                }

                                Spacer(modifier = Modifier.height(5.dp))

                                Spacer(modifier = Modifier.height(20.dp))
                            }
                            Spacer(modifier = Modifier.height(50.dp))
                            Button(
                                onClick = {
                                    var isValid = true

                                    if (accountNumber.isBlank()) {
                                        showToast(this@AccountSetActivity, "Please enter Account Number")
                                        isValid = false
                                    }

                                    if (cardNumber.isBlank()) {
                                        showToast(this@AccountSetActivity, "Please enter Card Number")
                                        isValid = false
                                    }

                                    if (expMonth.isBlank()) {
                                        showToast(this@AccountSetActivity, "Please enter Exp Month")
                                        isValid = false
                                    }

                                    if (expYear.isBlank()) {
                                        showToast(this@AccountSetActivity, "Please enter Exp Year")
                                        isValid = false
                                    }

                                    if (cvv.isBlank()) {
                                        showToast(this@AccountSetActivity, "Please enter CVV")
                                        isValid = false
                                    }
                                    if (isValid) {
                                        // Update the data in Firestore
                                        val userDocRef = db.collection("Users").document(userEmail)
                                        userDocRef.update(
                                            mapOf(
                                                "accountNumber" to accountNumber,
                                                "cardNumber" to cardNumber,
                                                "expMonth" to expMonth,
                                                "expYear" to expYear,
                                                "cvv" to cvv
                                            )
                                        )
                                            .addOnSuccessListener {
                                                showToast(this@AccountSetActivity, "Account information updated successfully")
                                                startActivity(intent)
                                            }
                                            .addOnFailureListener { exception ->
                                                showToast(this@AccountSetActivity, "Error updating account information: ${exception.message}")
                                            }
                                    }
                                },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(15.dp))
                                    .width(200.dp)
                                    .background(color = Color(0xff009177))
                                    .height(50.dp)
                                    .padding(20.dp, 0.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xff009177),
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = "Done", fontWeight = FontWeight.W700)

                            }
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

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}