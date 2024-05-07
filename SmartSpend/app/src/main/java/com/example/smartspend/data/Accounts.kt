package com.example.smartspend.data

import java.time.Instant
import java.util.Date
import java.util.UUID

data class Accounts(
    var id: UUID = UUID.randomUUID(),
    var username: String,
    var password: String,
    var firstname: String,
    var lastname: String,
    var phone: String,
    var address: String,
    var balance: Int,
    var budget: Int,
    var spend: Int,
    var CardNumber: String,
    var ExpMonth: Int,
    var ExpYear: Int,
    var CVV:Int,
    var AccountNumber:String,
    var accounttype:String,
    var entryDate: Date = Date.from(Instant.now())
)