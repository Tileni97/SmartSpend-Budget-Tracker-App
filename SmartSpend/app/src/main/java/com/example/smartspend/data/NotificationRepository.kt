package com.example.smartspend.data

object NotificationRepository {
    private val notification = mutableSetOf<NotificationData>()

    fun addNotification(notification: NotificationData) {
        this.notification.add(notification)
    }

    fun getAllNotification(): MutableSet<NotificationData> {
        return notification
    }
}