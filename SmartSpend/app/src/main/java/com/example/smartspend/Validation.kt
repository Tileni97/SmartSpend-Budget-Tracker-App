package com.example.smartspend

import android.content.Context
import android.widget.Toast

fun LoginValidation(password:String,username:String,context: Context):Boolean{
    var isvalid:Boolean = true
    if(username.isEmpty()){
        Toast.makeText(context,"Please Enter username",Toast.LENGTH_SHORT).show()
        isvalid = false
    }
    if(password.isEmpty()){
        Toast.makeText(context,"Please Enter password",Toast.LENGTH_SHORT).show()
        isvalid = false
    }

    else{
        isvalid = true
    }
    return isvalid

}