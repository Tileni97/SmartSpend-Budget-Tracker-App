package com.example.smartspend

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.core.app.NotificationCompat
import com.example.smartspend.data.UserRepository
import com.example.smartspend.ui.theme.SmartSpendTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.messaging

class MainActivity : ComponentActivity() {

    // Initialize Firebase Firestore
    private val db = FirebaseFirestore.getInstance()

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
                    //startActivity(intent)
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Landing()
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(30.dp, 0.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = {
                                startActivity(intent)
                            },
                            ) {
                                Text(text = "Continue...")
                            }
                        }
                    }
                }
            }
        }

        // Initialize Firebase Messaging Service
        Firebase.messaging.token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get the token
            val token = task.result

            // Store the token in Firestore for the logged-in user
            storeTokenInFirestore(token)
        })

        // Listen for new notifications in Firestore
        listenForNotifications()
    }

    private fun storeTokenInFirestore(token: String) {
        // Get the logged-in user's email or ID
        val userEmail = UserRepository.getEmail()

        // Store the token in Firestore
        val userRef = db.collection("Users").document(userEmail)
        userRef.update("fcmToken", token)
            .addOnSuccessListener { Log.d(TAG, "FCM token stored in Firestore") }
            .addOnFailureListener { e -> Log.w(TAG, "Error storing FCM token", e) }
    }

    private fun listenForNotifications() {
        val userEmail = UserRepository.getEmail()
        val notificationsRef = db.collection("Notifications").document(userEmail).collection("notifications")

        notificationsRef.addSnapshotListener { snapshots, error ->
            if (error != null) {
                Log.w(TAG, "Error listening for notifications", error)
                return@addSnapshotListener
            }

            for (documentChange in snapshots?.documentChanges ?: emptyList()) {
                if (documentChange.type == DocumentChange.Type.ADDED) {
                    val notification = documentChange.document.toObject(Notification::class.java)
                    notification?.let {
                        sendNotification(it.description.toString())
                    }
                }
            }
        }
    }

    private fun sendNotification(description: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val channelId = "notification_channel"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.icon)
            .setContentTitle("New Notification")
            .setContentText(description)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // For Android Oreo and higher versions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Notification Channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }

    data class Notification(
        val description: String? = null
    )

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(
                color = color
            )
        }
    }
}

// ... (rest of the code remains the same)


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