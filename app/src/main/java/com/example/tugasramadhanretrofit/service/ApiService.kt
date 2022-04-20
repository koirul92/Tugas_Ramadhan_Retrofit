package com.example.tugasramadhanretrofit.service

import com.example.tugasramadhanretrofit.model.PostLoginRequest
import com.example.tugasramadhanretrofit.model.PostLoginResponse
import com.example.tugasramadhanretrofit.model.RegisterRequest
import com.example.tugasramadhanretrofit.model.RegisterResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    fun register(@Body requestBody: RegisterRequest): Call<RegisterResponse>

    @POST("auth/login")
    fun login(@Body requestBody: PostLoginRequest): Call<PostLoginResponse>

    @GET("auth/me")
    fun auth(@Header("Authorization") token: String):Call<RegisterResponse>
}