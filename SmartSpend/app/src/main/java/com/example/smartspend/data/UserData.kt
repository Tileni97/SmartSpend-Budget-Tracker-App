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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        TODO("entryDate")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(phone)
        parcel.writeString(address)
        parcel.writeValue(balance)
        parcel.writeValue(budget)
        parcel.writeValue(spent)
        parcel.writeString(cardNumber)
        parcel.writeValue(expMonth)
        parcel.writeValue(expYear)
        parcel.writeValue(cvv)
        parcel.writeString(AccountNumber)
        parcel.writeString(accountType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserData> {
        override fun createFromParcel(parcel: Parcel): UserData {
            return UserData(parcel)
        }

        override fun newArray(size: Int): Array<UserData?> {
            return arrayOfNulls(size)
        }
    }
}