package com.example.smartspend.data

data class TransectionItem(
    val category:String? = null,
    val amount:String? = null,
    val type:String? = null,
    var documentId: String? = null,
    var entryDate: String? = null,
)