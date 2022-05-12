package com.example.demoproject.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String? = null

)
