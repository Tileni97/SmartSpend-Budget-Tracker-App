package com.example.smartspend.Screens.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartspend.Screens.home.ui.theme.SmartSpendTheme
@Composable
fun SettingScreen(){
    Text(text = "Profile")
}


@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingScreen()
}