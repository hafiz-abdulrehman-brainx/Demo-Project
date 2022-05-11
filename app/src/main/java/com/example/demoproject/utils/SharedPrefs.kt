package com.example.demoproject.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context){

   var editor:SharedPreferences.Editor
   var sharedPrefs:SharedPreferences = context.getSharedPreferences("userCredentials", Context.MODE_PRIVATE)

    init{
        editor = sharedPrefs.edit()
    }
    object Keys{
        const val isLogin = "login"
        const val accessToken = "token"
        const val clientId = "client"
        const val username = "username"
        const val password = "password"
    }
    var login: Boolean
    get() = sharedPrefs.getBoolean(Keys.isLogin,false)
    set(value) = editor.putBoolean(Keys.isLogin,value).apply()

    var token: String?
        get() = sharedPrefs.getString(Keys.accessToken,"default")
        set(value) = editor.putString(Keys.accessToken,value).apply()

    var client: String?
        get() = sharedPrefs.getString(Keys.clientId,"default")
        set(value) = editor.putString(Keys.clientId,value).apply()

    var username: String?
        get() = sharedPrefs.getString(Keys.username,"default")
        set(value) = editor.putString(Keys.username,value).apply()

    var password: String?
        get() = sharedPrefs.getString(Keys.password,"default")
        set(value) = editor.putString(Keys.password,value).apply()




}