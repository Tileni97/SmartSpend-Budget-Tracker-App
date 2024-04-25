package com.example.smartspend.data

import java.time.Instant
import java.util.Date
import java.util.UUID

data class TransectionItem(
    var id: UUID = UUID.randomUUID(),
    val Description:String,
    val ammount:Int,
    val type:String,
    var entryDate: Date = Date.from(Instant.now())
)