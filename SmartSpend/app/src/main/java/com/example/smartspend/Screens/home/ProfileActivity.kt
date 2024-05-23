package com.example.smartspend.Screens.home

import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme
import com.example.smartspend.data.UserRepository
import com.example.smartspend.data.dataBaseRepository
import com.example.smartspend.navigation.Routes

// Main composable function for the Profile screen
@Composable
fun ProfileScreen(navController: NavHostController) {
    var userFirstName by remember { mutableStateOf("") }
    var userLastName by remember { mutableStateOf("") }
    var userPhone by remember { mutableStateOf("") }
    var userAccountType by remember { mutableStateOf("") }
    var userAddress by remember { mutableStateOf("") }
    var userAccountNumber by remember { mutableStateOf("") }
    var userBranchCode by remember { mutableStateOf("") }
    var userEntryDate by remember { mutableStateOf("") }
    var showUpdateFields by remember { mutableStateOf(false) }
    var updatedAddress by remember { mutableStateOf("") }
    var updatedPhone by remember { mutableStateOf("") }
    var showResetConfirmation by remember { mutableStateOf(false) }

    val userEmail: String = UserRepository.getEmail()
    val context = LocalContext.current

    val db = dataBaseRepository.getDb()
    val userDocRef = db.collection("Users").document(userEmail)

    // Fetch user details from Firestore
    userDocRef.get().addOnSuccessListener { document ->
        var firstName = document.data?.get("firstName") as? String
        var lastName = (document.data?.get("lastName") as? String)
        var phone = document.data?.get("phone") as? String
        var accountType = document.data?.get("accountType") as? String
        var address = document.data?.get("address") as? String
        var account = document.data?.get("accountNumber") as? String
        var branch = document.data?.get("branchCode") as? String
        var since = document.data?.get("entryDate") as? String

        userFirstName = firstName.toString()
        userLastName = lastName.toString()
        userPhone = phone.toString()
        userAccountType = accountType.toString()
        userAddress = address.toString()
        userAccountNumber = account.toString()
        userBranchCode = branch.toString()
        userEntryDate = since.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        ProfileScreenTopBar(navController)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${userFirstName} ${userLastName}",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = userAddress,
            style = TextStyle(
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = userEmail,
            style = TextStyle(
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = userPhone,
            style = TextStyle(
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Account Type: ${userAccountType}",
            style = TextStyle(
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Account Number: ${userAccountNumber}",
            style = TextStyle(
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Branch Code: ${userBranchCode}",
            style = TextStyle(
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Since: ${userEntryDate}",
            style = TextStyle(
                fontSize = 16.sp
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Update User Details",
            modifier = Modifier
                .clickable {
                    showUpdateFields = true
                    updatedAddress = userAddress
                    updatedPhone = userPhone
                }
                .padding(8.dp),
            color = Color(0xff009177),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        )

        if (showUpdateFields) {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = updatedAddress,
                onValueChange = { updatedAddress = it },
                label = {
                    Text(
                        text = "Residential Address",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                    )
                },
                placeholder = {
                    Text(
                        text = "Erf 123, Hoba Street, Windhoek",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                    )
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = updatedPhone,
                onValueChange = { updatedPhone = it },
                label = {
                    Text(
                        text = "Phone Number",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                    )
                },
                placeholder = {
                    Text(
                        text = "081 123 4567",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W400,
                    )
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Check if text fields are empty
                    if (updatedAddress.isBlank() || updatedPhone.isBlank()) {
                        Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    } else {
                        // Update user details in Firestore
                        userDocRef.update(
                            mapOf(
                                "address" to updatedAddress,
                                "phone" to updatedPhone
                            )
                        )
                            .addOnSuccessListener {
                                // Fetch updated user details
                                userDocRef.get().addOnSuccessListener { document ->
                                    val updatedAddress = document.data?.get("address") as? String
                                    val updatedPhone = document.data?.get("phone") as? String

                                    // Update the displayed values
                                    userAddress = updatedAddress.toString()
                                    userPhone = updatedPhone.toString()

                                    // Show success toast
                                    Toast.makeText(context, "Details Successfully Updated", Toast.LENGTH_LONG).show()
                                }
                            }

                        showUpdateFields = false
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
                Text(
                    text = "Update",
                    color = Color.White
                )
            }


        }


        Spacer(modifier = Modifier.height(480.dp))

        Text(
            text = "RESET BUDGETS AND SPENDINGS",
            modifier = Modifier
                .clickable {
                    showResetConfirmation = true
                }
                .padding(8.dp),
            color = Color(0xff009177),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        )

        if (showResetConfirmation) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Remove all sub-documents from "transactions" > "userEmail" > "transactions"
                    db.collection("transections")
                        .document(userEmail)
                        .collection("transections")
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                document.reference.delete()
                            }
                        }

                    // Update "budget" and "spent" fields to "0" from "Categories" > "userEmail" > "budget"
                    val categoriesRef = db.collection("Categories").document(userEmail).collection("budget").document("accomodation")
                    val categoriesRef1 = db.collection("Categories").document(userEmail).collection("budget").document("education")
                    val categoriesRef2 = db.collection("Categories").document(userEmail).collection("budget").document("food")
                    val categoriesRef3 = db.collection("Categories").document(userEmail).collection("budget").document("health")
                    val categoriesRef4 = db.collection("Categories").document(userEmail).collection("budget").document("transport")
                    categoriesRef.update(
                        mapOf(
                            "budget" to "0",
                            "spent" to "0",
                        )

                    )
                    categoriesRef1.update(
                        mapOf(
                            "budget" to "0",
                            "spent" to "0",
                        )

                    )
                    categoriesRef2.update(
                        mapOf(
                            "budget" to "0",
                            "spent" to "0",
                        )

                    )
                    categoriesRef3.update(
                        mapOf(
                            "budget" to "0",
                            "spent" to "0",
                        )

                    )
                    categoriesRef4.update(
                        mapOf(
                            "budget" to "0",
                            "spent" to "0",
                        )

                    )



                    // Update "budget" and "spent" fields to "0" from "Users" > "userEmail"
                    userDocRef.update(
                        mapOf(
                            "budget" to "0",
                            "spent" to "0"
                        )
                    )

                    // Show success toast
                    Toast.makeText(context, "Users Budgets Reset Successfully", Toast.LENGTH_LONG).show()

                    showResetConfirmation = false
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
                Text(
                    text = "Confirm",
                    color = Color.White
                )
            }
        }

    }
}

@Composable
fun ProfileScreenTopBar(navController: NavHostController){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xff009177)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .padding(5.dp)
                    .clickable { navController.popBackStack() }

            ){
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "", tint = Color.White)
            }
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Profile",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.White
                )
            }
            Box (
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 5.dp))
                    .padding(5.dp)
                    .clickable {navController.navigate(Routes.HelpScreen.routes)}

            ){
                Icon(imageVector = Icons.Outlined.Info, contentDescription = "",tint = Color.White)
            }
        }
    }
}

// Preview for Profile screen
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SmartSpendTheme {
        val navController = rememberNavController()
        ProfileScreen(navController)
    }
}