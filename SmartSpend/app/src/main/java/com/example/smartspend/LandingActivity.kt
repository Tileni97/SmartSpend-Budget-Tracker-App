package com.example.smartspend

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowCircleRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartspend.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * LandingActivity to display a welcome screen with a button to proceed to Login.
 */
class LandingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartSpendTheme {
                SetBarColor(color = Color(0xff009177))
                val intent = Intent(this, AccountSetActivity::class.java)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xff009177)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        welcoming()
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp, 0.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = {
                                    startActivity(intent)
                                },
                                modifier = Modifier
                                    .requiredHeight(50.dp)
                                    .requiredWidth(200.dp)
                            ) {
                                Text(
                                    text = "Proceed",
                                    fontWeight = FontWeight.W700,
                                    fontSize = 20.sp
                                )
                                Spacer(modifier = Modifier.size(20.dp))
                                Icon(imageVector = Icons.Rounded.ArrowCircleRight, contentDescription = "")
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Function to set the system bar color.
     */
    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(color = color)
        }
    }
}

/**
 * Composable function to display a welcome message and image.
 */
@Composable
fun welcoming() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.phonecard),
            contentDescription = "Card 18",
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = "Manage Your Spending with SmartSpend",
            style = TextStyle(fontSize = 30.sp),
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SmartSpendTheme {
        welcoming()
    }
}
