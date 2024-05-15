package com.example.smartspend.data

import android.annotation.SuppressLint

object TransectionRepository {
    private val transection = mutableSetOf<TransectionItem>()

    @SuppressLint("RestrictedApi")
    fun getTransection(): MutableSet<TransectionItem> {
        return transection
    }

    fun addTransection(trans: TransectionItem) {
        transection.add(trans)
    }

    fun clearTransection() {
        transection.clear()
    }

}