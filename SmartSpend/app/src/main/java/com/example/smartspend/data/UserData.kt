package com.example.smartspend.data

import android.os.Parcel
import android.os.Parcelable
import java.time.Instant
import java.util.Date

data class UserData(
    //var id: UUID = UUID.randomUUID(),
    var username: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var phone: String? = null,
    var address: String? = null,
    var balance: Int? = null,
    var budget: Int? = null,
    var spent: Int? = null,
    var cardNumber: String? = null,
    var expMonth: Int? = null,
    var expYear: Int? = null,
    var cvv:Int? = null,
    var AccountNumber:String? = null,
    var accountType:String? = null,
    var entryDate: Date? = Date.from(Instant.now())
)