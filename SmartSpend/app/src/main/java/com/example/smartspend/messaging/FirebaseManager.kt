package com.example.smartspend.messaging

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseManager(private val context: Context) {
    private val db = FirebaseFirestore.getInstance()
    private val TAG = "FirebaseManager"

    fun storeTokenInFirestore(token: String, userEmail: String) {
        // Get the logged-in user's email or ID
        //val userEmail = UserRepository.getEmail()

        // Store the token in Firestore
        val userRef = db.collection("Users").document(userEmail)
        userRef.update("fcmToken", token.trim())
            .addOnSuccessListener {
                Log.d(TAG, "FCM token stored in Firestore")
                showToast(context, "FCM token stored in Firestore: $token")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error storing FCM token", e)
                showToast(context, "Error storing FCM token")
            }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}