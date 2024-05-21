package com.example.smartspend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContent {
            SmartSpendTheme {
                SetBarColor(color = Color(0xff009177))
                val intent = Intent(this, LandingActivity::class.java)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xff009177)
                ) {

                    FlashAndNavigate()

                }
            }
        }

    }
    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }

    @Composable
    private fun FlashAndNavigate() {
        val intent = Intent(this, LandingActivity::class.java)
        LaunchedEffect(Unit) {
            delay(2000) // Delay for 500 milliseconds (adjust as needed)
            startActivity(intent)
            //finish()
        }
        Landing()
    }

}


@Composable
fun Landing() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        // Logo
        Spacer(modifier = Modifier.size(100.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = "S",
                color =Color.White,
                fontSize = 100.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Text(
                text = "S",
                color = Color.Green,
                fontSize = 100.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .scale(1f, -1f) // Inverted vertically
                    .rotate(degrees = 180f) // Rotated 180 degrees to correct orientation
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // SmartSpend app title label
        Text(
            text = "SmartSpend",
            color = Color.White,
            fontSize = 30.sp,
        )

        Spacer(modifier = Modifier.size(100.dp))

    }
}


@Preview(showBackground = true)
@Composable
fun LandingPreview() {
    SmartSpendTheme {
        Landing()
    }
}
