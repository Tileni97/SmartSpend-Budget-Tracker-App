package com.example.smartspend.data

import java.time.Instant
import java.util.Date
import java.util.UUID

data class Categories(
    var id: UUID = UUID.randomUUID(),
    var name: String,
    var budgetAlocated: Int,
    var spand: Int,
    var remainingBudget: Int,
    var entryDate: Date = Date.from(Instant.now())
)