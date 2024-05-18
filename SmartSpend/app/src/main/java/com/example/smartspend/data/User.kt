package com.example.smartspend.data

import java.time.Instant
import java.util.Date
import java.util.UUID

data class User(
    var id: UUID = UUID.randomUUID(),
    var username: String,
    var firstName: String,
    var lastName: String,
    var phone: String,
    var address: String,
    var balance: Int,
    var budget: Int,
    var spent: Int,
    var cardNumber: String,
    var expMonth: Int,
    var expYear: Int,
    var cvv:Int,
    var AccountNumber:String,
    var accountType:String,
    var entryDate: Date = Date.from(Instant.now())
)