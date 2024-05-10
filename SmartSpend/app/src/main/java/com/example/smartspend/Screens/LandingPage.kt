package com.example.smartspend.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.rotate

@Composable
fun Onboarding1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xff009177)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            // Logo
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "S",
                    color = Color.White,
                    fontSize = 44.901641845703125.sp,
                )
                Text(
                    text = "S",
                    color = Color.White,
                    fontSize = 44.901641845703125.sp,
                    modifier = Modifier
                        .scale(1f, -1f) // Inverted vertically
                        .rotate(degrees = 180f) // Rotated 180 degrees to correct orientation
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // SmartSpend app title label
            Text(
                text = "SmartSpend",
                color = Color.White,
                fontSize = 20.sp,
            )
        }
    }
}

@Preview(widthDp = 411, heightDp = 886)
@Composable
private fun Onboarding1Preview() {
    Onboarding1()
}
