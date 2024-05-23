package com.example.smartspend.admin

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeRuntimeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.data.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.Instant
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUser(){

    var amount by remember { mutableStateOf("") }
    var accountNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    var cardNumber by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var ExpMonth by remember { mutableStateOf("") }
    var ExpYear by remember { mutableStateOf("") }

    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Column (
            modifier = Modifier
               .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                TextField(value = accountNumber, onValueChange = {accountNumber = it},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Account Number",color = Color(0xff009177))},
                    placeholder = {Text(text = "0.00")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.AccountTree, contentDescription = "")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextField(value = amount, onValueChange = {amount = it},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Amount",color = Color(0xff009177))},
                    placeholder = {Text(text = "0.00")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Money, contentDescription = "")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                Row (
                    modifier = Modifier
                       .width(294.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box (
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp, 0.dp),
                        contentAlignment = Alignment.Center
                    ){
                        TextField(value = firstName, onValueChange = {firstName = it},
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent),
                            label = {Text(text = "First Name",color = Color(0xff009177))},
                            placeholder = {Text(text = "Shikongo")},
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
                    }
                    Box (
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp, 0.dp),
                        contentAlignment = Alignment.Center
                    ){
                        TextField(value = lastName, onValueChange = {lastName = it},
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent),
                            label = {Text(text = "Last Name",color = Color(0xff009177))},
                            placeholder = {Text(text = "Giideon")},
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
                    }
                }
                TextField(value = address, onValueChange = {address = it},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Residence Address",color = Color(0xff009177))},
                    placeholder = {Text(text = "Grysblock, Windhoek Namibia")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.LocationOn, contentDescription = "")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                TextField(value = phoneNumber, onValueChange = {phoneNumber = it},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Phone Number",color = Color(0xff009177))},
                    placeholder = {Text(text = "\n" +
                            "                    singleLine = true,+264 81 000 0000")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Phone, contentDescription = "")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
            }
            Column {
                TextField(value = cardNumber, onValueChange = {cardNumber = it},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent),
                    label = {Text(text = "Card Number",color = Color(0xff009177))},
                    placeholder = {Text(text = "0.00")},
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.AccountTree, contentDescription = "")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Row (
                    modifier = Modifier
                        .width(294.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Box (
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp, 0.dp),
                        contentAlignment = Alignment.Center
                    ){
                        ExpMonth = ExpMonth()
                    }
                    Box (
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp, 0.dp),
                        contentAlignment = Alignment.Center
                    ){

                       ExpYear = ExpYear()
                    }
                    Box (
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp, 0.dp),
                        contentAlignment = Alignment.Center
                    ){
                        TextField(value = lastName, onValueChange = {lastName = it},
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.Transparent),
                            label = {Text(text = "Cvv",color = Color(0xff009177))},
                            placeholder = {Text(text = "321")},
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
                    }
                }
            }
            Button(
                onClick = {
                    val db = FirebaseFirestore.getInstance()
                    val registeredUser = UserRepository.getRegisteredUser()
                    db.collection("Users").document(registeredUser)
                        .set(
                            hashMapOf(
                                "accountNumber" to accountNumber,
                                "balance" to amount,
                                "firstName" to firstName,
                                "lastName" to lastName,
                                "address" to address,
                                "phone" to phoneNumber,
                                "budget" to "0",
                                "spent" to "0",
                                "branchCode" to "0",
                                "cvv" to "0",
                                "expMonth" to "0",
                                "expYear" to "0",
                                "entryDate" to "${Date.from(Instant.now())}",
                                "username" to registeredUser.toString(),
                                "cardNumber" to "0",
                                "accountType" to "Cheque"

                            )
                        )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(50.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff009177),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Submit")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpMonth():String {
    val context = LocalContext.current
    val reason = arrayOf("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(reason[0]) }

    //Fetch User Email
    var userEmail: String = UserRepository.getEmail()

    // Initialize Firebase Firestore
    val db = FirebaseFirestore.getInstance()

    Box(
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            Text(
                text = "Exp Month",
                color = Color(0xff009177)
            )
            TextField(
                value = selectedText,
                onValueChange = {selectedText = it},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                reason.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }

    return selectedText.toString()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpYear():String {
    val context = LocalContext.current
    val reason = arrayOf("24", "25", "26", "27", "28", "29", "30", "31")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(reason[0]) }

    //Fetch User Email
    var userEmail: String = UserRepository.getEmail()

    // Initialize Firebase Firestore
    val db = FirebaseFirestore.getInstance()

    Box(
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            Text(
                text = "Exp Year",
                color = Color(0xff009177)
            )
            TextField(
                value = selectedText,
                onValueChange = {selectedText = it},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent)
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                reason.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }

    return selectedText.toString()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CreateUser()
}