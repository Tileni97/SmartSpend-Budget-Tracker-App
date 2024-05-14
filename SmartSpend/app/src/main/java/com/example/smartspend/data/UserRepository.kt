package com.example.smartspend.data

import android.annotation.SuppressLint
import com.google.firebase.firestore.auth.User

object UserRepository {
    private val users = mutableSetOf<UserData>()

    @SuppressLint("RestrictedApi")
    fun getUsers(): MutableSet<UserData> {
        return users
    }
    fun addUser(user: UserData) {
        users.add(user)
    }

}