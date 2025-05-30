package com.example.smartspend

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import com.example.smartspend.data.UserRepository
import com.example.smartspend.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    // Initialize Firebase Firestore
    val db = FirebaseFirestore.getInstance()

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
                    if (isNetworkAvailable(this)){
                        FlashAndNavigate()
                        //finish()
                    }
                    else {
                        Column (
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                text = "No Internet Connection",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Image(
                                painter = painterResource(id = R.drawable.noconnection),
                                contentDescription = "Card 18",
                                modifier = Modifier
                                    .requiredWidth(200.dp)
                            )
                            Button(onClick = {
                                if(isNetworkAvailable(this@MainActivity)){
                                    startActivity(Intent(this@MainActivity, MainActivity::class.java))
                                }
                                else {
                                    Toast.makeText(this@MainActivity, "Please make sure you have an active internet connection before trying again", Toast.LENGTH_SHORT).show()
                                }
                            }) {
                                Text(text = "Retry")
                            }
                        }
                    }
                    

                }
            }
        }

        // Request exemption from battery optimizations
        requestBatteryOptimizationExemption()

    }

    private fun requestBatteryOptimizationExemption() {
        val intent = Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        intent.data = Uri.parse("package:${packageName}")
        startActivity(intent)
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

// ... (rest of the code remains the same)
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
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

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}