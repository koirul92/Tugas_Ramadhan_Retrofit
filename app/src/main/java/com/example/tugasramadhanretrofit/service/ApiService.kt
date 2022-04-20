package com.example.tugasramadhanretrofit.service

import com.example.tugasramadhanretrofit.model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("auth/register")
    fun register(@Body requestBody: RegisterRequest): Call<RegisterResponse>

    @POST("auth/login")
    fun login(@Body requestBody: PostLoginRequest): Call<PostLoginResponse>

    @GET("auth/me")
    fun auth(@Header("Authorization") token: String):Call<RegisterResponse>

    @PUT("v1/users")
    fun edit(@Header("Authorization")token: String,@Body requestBody: EditRequest ):Call<RegisterResponse>
}