package com.example.tugasramadhanretrofit.model


import com.google.gson.annotations.SerializedName

data class PostLoginResponse(
    @SerializedName("data")
    val data: LoginData,
    @SerializedName("success")
    val success: Boolean
)

data class LoginData(
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("username")
    val username: String
)