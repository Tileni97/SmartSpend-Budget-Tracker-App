package com.example.smartspend.Screens.home




import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
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

// ProfileDetailsScreen composable displays the user's profile details and allows editing phone number and address.
@Composable
fun ProfileDetailsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val db = dataBaseRepository.getDb()
    var userEmail: String = UserRepository.getEmail()

    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var isLoading by remember { mutableStateOf(true) }

    // Fetch user data from Firestore when the screen is composed
    LaunchedEffect(Unit) {
        val userDocRef = db.collection("Users").document(userEmail)
        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    fullName =
                        TextFieldValue(document.getString("firstName") + " " + document.getString("lastName"))
                    phone = TextFieldValue(document.getString("phone") ?: "")
                    address = TextFieldValue(document.getString("address") ?: "")
                } else {
                    Log.d(TAG, "No such document")
                }
                isLoading = false // Data loaded, hide loading indicator
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
                isLoading = false // Even on failure, hide loading indicator
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
            text = "Profile Details",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display full name (non-editable)
        OutlinedTextField(
            value = fullName,
            onValueChange = {}, // Make it non-editable
            label = { Text("Full Name") },
            enabled = false, // Disable editing
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Editable phone number and address fields
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Save button
        Button(
            onClick = {
                // Update Firestore with new phone and address
                val userDocRef = db.collection("Users").document(userEmail)
                userDocRef.update(
                    mapOf(
                        "phone" to phone.text,
                        "address" to address.text
                    )
                )
                    .addOnSuccessListener {
                        Toast.makeText(context, "Profile updated successfully", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(
                            context,
                            "Error updating profile: ${exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Save Changes")
        }

        // Loading indicator
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

    @Preview(showBackground = true)
    @Composable
            fun ProfileDetailsScreenPreview() {
        SmartSpendTheme {
            ProfileDetailsScreen(navController = rememberNavController())
        }
    }

