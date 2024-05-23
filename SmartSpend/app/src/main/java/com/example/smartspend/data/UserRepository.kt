package com.example.smartspend.data

import android.annotation.SuppressLint

object UserRepository {
    private val users = mutableSetOf<UserData>()
    private var email:String = mutableSetOf("").toString()
    private var registeredemail:String = ""
    private var isLogin:String = ""

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
        this.registeredemail = email
    }
    fun getRegisteredUser(): String{
        return registeredemail
    }

    fun setLogin(email: String){
        this.isLogin = email
    }
    fun isLogin(): String{
        return isLogin
    }


}