package com.example.smartspend.data

import java.util.Date

data class TransectionItem(
    val category:String? = null,
    val amount:String? = null,
    val type:String? = null,
    var documentId: String? = null,
    var entryDate: Date? = null,
)