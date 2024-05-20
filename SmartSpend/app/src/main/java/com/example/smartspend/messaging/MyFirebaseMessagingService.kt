package com.example.smartspend.messaging


import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Check if the message contains data payload
        if (remoteMessage.data.isNotEmpty()) {
            handleDataMessage(remoteMessage.data)
        } else {
          //  handleNotificationMessage(remoteMessage.notification)
        }
    }

    private fun handleDataMessage(data: Map<String, String>) {
        // Handle data payload
        // ...
    }

   /*
    private fun handleNotificationMessage(notification: RemoteMessage.Notification?) {
        if (notification != null) {
            val title = notification.title
            val body = notification.body

            // Modify the notification content if needed
            val modifiedTitle = "New Balance: ${title?.substringAfter(": ")}"
            val modifiedBody = "Your new balance is: $body"

            showBalanceNotification(modifiedTitle, modifiedBody)
        }
    }

    private fun showBalanceNotification(title: String, body: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val channelId = "balance_updates"
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.icon)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // For Android Oreo and higher versions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Balance Updates", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }*/
}