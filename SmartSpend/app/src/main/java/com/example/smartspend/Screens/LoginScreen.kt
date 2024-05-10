package com.example.smartspend.Screens

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.delay


/**
 * Shows a notification toast with the given message.
 *
 * @param context The current context.
 * @param message The message to display in the toast.
 */
private suspend fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    // Delay for demonstration purposes
    delay(1000)
    println("Toast: $message")
}
