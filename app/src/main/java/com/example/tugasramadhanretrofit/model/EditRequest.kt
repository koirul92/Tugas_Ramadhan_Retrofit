package com.example.tugasramadhanretrofit.model

import com.google.gson.annotations.SerializedName

data class EditRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String
)
