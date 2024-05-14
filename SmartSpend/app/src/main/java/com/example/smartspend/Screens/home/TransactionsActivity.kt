package com.example.smartspend.Screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.R
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme

// Main composable function for the Transaction screen
@Composable
fun TransactionScreen() {
    // Box composable with rounded corner shape, background color, and fixed height
    Box(
        modifier = Modifier
            .fillMaxWidth() // Fill the maximum available width
            .clip(RoundedCornerShape(bottomEnd = 100.dp)) // Apply rounded corner shape to the bottom-end corner
            .height(400.dp) // Set a fixed height of 400dp
            .background(color = Color(0xff009177)) // Set the background color to yellow
    ) {
        // Image composable inside the Box, filling the maximum size and cropping to fit
        Image(
            modifier = Modifier.fillMaxSize(), // Fill the maximum available size
            painter = painterResource(id = R.drawable.transact), // Set the image resource
            contentDescription = "happy woman", // Content description for accessibility
            contentScale = ContentScale.Crop // Crop the image to fit the available space while maintaining aspect ratio
        )
    }

    // Column composable for the content below the image
    Column(
        modifier = Modifier
            .fillMaxSize() // Fill the maximum available size
            .padding(horizontal = 16.dp), // Add horizontal padding
        //verticalArrangement = Arrangement.Center, // Center the content vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center the content horizontally
    ) {
        Spacer(modifier = Modifier.
                    size(320.dp)) // Add vertical spacing of 15dp

        // Title text with styling
        Text(
            text = "Manage your finances \nANYTIME, ANYWHERE",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            ),
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp)) // Vertical spacing
        // Subtitle text with styling
        Text(
            text = "Secure your financial future with our trusted budgeting services",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Spacer(modifier = Modifier.height(32.dp)) // Vertical spacing
        // Row of buttons with styling
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Transfer button
            Button(
                onClick = { /* Handle update */ },
                modifier = Modifier.height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Transfer",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            // Pay button
            Button(
                onClick = { /* Handle update */ },
                modifier = Modifier.height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Pay",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            // Buy button
            Button(
                onClick = { /* Handle update */ },
                modifier = Modifier.height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = "Buy",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

// Preview for Transaction screen
@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    SmartSpendTheme {
        TransactionScreen()
    }
}