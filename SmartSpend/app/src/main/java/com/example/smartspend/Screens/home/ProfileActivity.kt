package com.example.smartspend.Screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartspend.R
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme

// Main composable function for the Profile screen
@Composable
fun ProfileScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title text
            Text(
                text = "Update your info",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // List of avatar drawable resources
            val avatars = listOf(
                R.drawable.avatar1,
                R.drawable.avatar2,
                R.drawable.avatar3
            )

            // State variables to track selected avatar and avatar list visibility
            var selectedAvatarIndex by remember { mutableStateOf(0) }
            var showAvatarList by remember { mutableStateOf(false) }

            // Display the selected avatar
            Image(
                painter = painterResource(avatars[selectedAvatarIndex]),
                contentDescription = "Avatar",
                modifier = Modifier.size(300.dp)
            )

            // Edit avatar icon
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit Avatar",
                modifier = Modifier
                    .size(30.dp)
                    .clickable { showAvatarList = true }
                    .padding(bottom = 16.dp)
            )

            // Show avatar list when showAvatarList is true
            if (showAvatarList) {
                AvatarList(
                    avatars = avatars,
                    selectedAvatarIndex = selectedAvatarIndex,
                    onAvatarSelected = { index ->
                        selectedAvatarIndex = index
                        showAvatarList = false
                    }
                )
            }

            // User details input fields
            UserDetailsFields()

            // Update button
            Button(
                onClick = { /* Handle update */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Update")
            }
        }
    }
}

// Composable function to display the list of avatars
@Composable
fun AvatarList(
    avatars: List<Int>,
    selectedAvatarIndex: Int,
    onAvatarSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .width(200.dp) // Limit the width of the row
            .horizontalScroll(rememberScrollState()) // Enable horizontal scrolling
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Loop through the list of avatars and display each one
        for (i in avatars.indices) {
            Avatar(
                painter = painterResource(avatars[i]),
                isSelected = i == selectedAvatarIndex,
                onClick = { onAvatarSelected(i) }
            )
        }
    }
}

// Composable function for displaying an avatar
@Composable
fun Avatar(
    painter: Painter,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Image(
        painter = painter,
        contentDescription = "Avatar",
        modifier = Modifier
            .size(80.dp) // Adjust the size of the avatar
            .clickable(onClick = onClick)
            .border(
                width = if (isSelected) 4.dp else 0.dp, // Add a border for the selected avatar
                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                shape = CircleShape
            )
    )
}

// Composable function for user details input fields
@Composable
fun UserDetailsFields() {
    OutlinedTextField(
        value = "User's Name", // Example placeholder
        onValueChange = { /* Handle name change */ },
        label = { Text("Name") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = "User's Address", // Example placeholder
        onValueChange = { /* Handle address change */ },
        label = { Text("Address") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = "User's Phone", // Example placeholder
        onValueChange = { /* Handle phone change */ },
        label = { Text("Phone") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))
}

// Preview for Profile screen
@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SmartSpendTheme {
        ProfileScreen()
    }
}