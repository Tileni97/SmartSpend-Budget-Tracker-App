package com.example.smartspend.data

import java.time.Instant
import java.util.Date
import java.util.UUID

data class TransectionItem(
    val category:String? = null,
    val ammount:String? = null,
    val type:String? = null,
    var documentId: String? = null,
    var entryDate: String? = null
)