package com.example.demoproject.repository

import com.example.demoproject.api.RetrofitInstance
import com.example.demoproject.model.TrainingCategories
import com.example.demoproject.model.User
import retrofit2.Call

class Repository {
    fun getTrainingCategories(
        contentType: String, accessToken: String, client: String, uid: String
    ): Call<TrainingCategories> {
        return RetrofitInstance.api.getTrainingCategories(contentType, accessToken, client, uid)
    }

    fun loginUser(user: User): Call<User> {
        return RetrofitInstance.api.loginUser(user)
    }

}