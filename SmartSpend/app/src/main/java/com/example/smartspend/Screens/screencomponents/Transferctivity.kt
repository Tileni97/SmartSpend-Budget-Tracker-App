package com.example.smartspend.Screens.screencomponents

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartspend.LandingActivity
import com.example.smartspend.Screens.screencomponents.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun TransferScreen(){
    Text(text = "Transfer Screen")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {

    TransferScreen()

}