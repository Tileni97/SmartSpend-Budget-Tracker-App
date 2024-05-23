package com.example.smartspend.Screens.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.smartspend.Screens.screencomponents.ui.theme.SmartSpendTheme
import com.example.smartspend.data.UserRepository
import com.example.smartspend.data.dataBaseRepository
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun AccountDetailsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = dataBaseRepository.getDb()
    var userEmail: String = UserRepository.getEmail()

    var accountNumber by remember { mutableStateOf(TextFieldValue("")) }
    var branch by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch user data from Firestore when the screen is composed
    LaunchedEffect(Unit) {
        val userDocRef = db.collection("Users").document(userEmail)
        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    accountNumber = TextFieldValue(document.getString("accountNumber") ?: "")
                    branch = TextFieldValue(document.getString("branch") ?: "") // Assuming you have a "branch" field
                    city = TextFieldValue(document.getString("city") ?: "")    // Assuming you have a "city" field
                } else {
                    Log.d(TAG, "No such document")
                }
                isLoading = false
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
                isLoading = false
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
        Text(
            text = "Account Details",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display account details in outlined boxes
        DetailItemBox(label = "Account Number", value = accountNumber.text)
        DetailItemBox(label = "Branch", value = branch.text)
        DetailItemBox(label = "City", value = city.text)

        // Loading indicator
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun DetailItemBox(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) // Add slight elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = label, fontWeight = FontWeight.Bold)
            Text(text = value, modifier = Modifier.padding(top = 4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AccountDetailsScreenPreview() {
    SmartSpendTheme {
        AccountDetailsScreen(navController = rememberNavController())
    }
}
