package com.example.smartspend.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.smartspend.R

@Composable
fun Onboarding2(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xff009177)),
            contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .offset(x = 0.dp, y = 88.dp)
                .requiredWidth(width = 307.dp)
                .requiredHeight(height = 486.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.phonecard),
                contentDescription = "Card 18",

                )
        }
        Text(
            text = "Manage Your Money with SmartSpend",
            color = Color.White,
            style = TextStyle(fontSize = 35.sp),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 70.dp, y = 421.dp)
                .requiredWidth(width = 340.dp)
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 256.dp, y = 706.dp)
                .requiredWidth(width = 101.dp)
                .requiredHeight(height = 55.dp)
        ) {
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .requiredHeight(height = 55.dp)
                    .requiredWidth(width = 200.dp)
                    .align(Alignment.BottomEnd) // Align the button to the bottom end (bottom right) corner
            ) {
                Text(
                    text = "Proceed",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(8.dp) // Add padding to center the text
                )
            }
        }


    }
}

@Preview(widthDp = 411, heightDp = 866)
@Composable
private fun Onboarding2Preview() {
    Onboarding2()
}
