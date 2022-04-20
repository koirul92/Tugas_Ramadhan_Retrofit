package com.example.tugasramadhanretrofit.model


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    val data: RegisterData,
    @SerializedName("success")
    val success: Boolean
)
data class RegisterData(
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val _id: String,
    @SerializedName("username")
    val username: String
)