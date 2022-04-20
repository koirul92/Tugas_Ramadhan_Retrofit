package com.example.tugasramadhanretrofit

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tugasramadhanretrofit.databinding.ActivityLoginBinding
import com.example.tugasramadhanretrofit.databinding.ActivityMainBinding
import com.example.tugasramadhanretrofit.model.RegisterRequest
import com.example.tugasramadhanretrofit.model.RegisterResponse
import com.example.tugasramadhanretrofit.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferences = this.getSharedPreferences(LoginActivity.FILENAME, Context.MODE_PRIVATE)
        binding.tvToken.text = "Token : ${sharedPreferences.getString("token","default")}"
        val token = sharedPreferences.getString("token", "default_email")
        auth(token)

    }

    private fun auth(token:String?) {
        ApiClient.instance.auth(token = "Bearer $token").enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                val body = response.body()
                when (response.code()) {
                    200 -> {
                        binding.pbLoading.visibility = View.GONE
                        binding.tvEmail.text = "Email : ${body?.data?.email}"
                        binding.tvID.text = "ID : ${body?.data?.email}"
                        binding.tvUsername.text = "Username : ${body?.data?.email}"
                    }
                    else -> {
                        binding.pbLoading.visibility = View.GONE
                        Toast.makeText(this@MainActivity, "Registrasi Gagal!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

}