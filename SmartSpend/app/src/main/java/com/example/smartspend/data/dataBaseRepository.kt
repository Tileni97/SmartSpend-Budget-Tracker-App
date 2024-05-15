package com.example.smartspend.data

import android.annotation.SuppressLint
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

@SuppressLint("StaticFieldLeak")
object dataBaseRepository {
    private val db = Firebase.firestore

    fun getDb(): FirebaseFirestore {
        return db
    }
}