package com.example.smartspend.data

import java.time.Instant
import java.util.Date
import java.util.UUID

data class Cards(
    var id: UUID = UUID.randomUUID(),
    var userID: String,
    var cardNumber: Int,
    var expMonth: Int,
    var expYear: Int,
    var cvv:Int,
    var entryDate: Date = Date.from(Instant.now())
)