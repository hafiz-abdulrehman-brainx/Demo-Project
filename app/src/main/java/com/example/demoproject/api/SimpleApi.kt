package com.example.demoproject.api

import com.example.demoproject.model.Qoute
import com.example.demoproject.model.User
import retrofit2.Call
import retrofit2.http.*

interface SimpleApi {
    @Headers("content-type: application/json" )
    @POST("/api/v1/users/sign_in")
    fun loginUser(@Body  user:User): Call<User>

    @GET("api/v1/quotes/random")
    suspend fun getQoute(
        @Header("content-type") contentType:String,
        @Header("access-token") accessToken:String,
        @Header("client") client:String,
        @Header("uid") uid:String
    ):Qoute
}