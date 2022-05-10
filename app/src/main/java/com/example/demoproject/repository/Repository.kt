package com.example.demoproject.repository

import com.example.demoproject.api.RetrofitInstance
import com.example.demoproject.model.Qoute
import com.example.demoproject.model.User
import retrofit2.Call
import retrofit2.Retrofit

class Repository {
    suspend fun getQoute(
        contentType:String,accessToken:String,client:String,uid:String
    ):Qoute{
        return RetrofitInstance.api.getQoute(contentType,accessToken,client,uid)
    }
    suspend fun loginUser(user:User): Call<User>  {
        return RetrofitInstance.api.loginUser(user)
    }
}