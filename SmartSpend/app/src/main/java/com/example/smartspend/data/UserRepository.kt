package com.example.smartspend.data

import android.annotation.SuppressLint
import com.google.firebase.firestore.auth.User

object UserRepository {
    private val users = mutableSetOf<UserData>()
    private var email:String = mutableSetOf("").toString()

    @SuppressLint("RestrictedApi")
    fun getUsers(): MutableSet<UserData> {
        return users
    }
    fun addUser(user: UserData) {
        users.add(user)
    }
    fun setEmail(email: String){
        this.email = email
    }
    fun getEmail(): String{
        return email
    }
    fun setRegisteredUser(email: String){
        this.email = email
    }
    fun getRegisteredUser(): String{
        return email
    }


}