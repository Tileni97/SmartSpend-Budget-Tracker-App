package com.example.smartspend.data

import java.time.Instant
import java.util.Date
import java.util.UUID

data class Notifications(
    var id: UUID = UUID.randomUUID(),
    var title: String,
    var description: String,
    var userId: String,
    var isRead: Boolean = false,
    var entryDate: Date = Date.from(Instant.now())
)