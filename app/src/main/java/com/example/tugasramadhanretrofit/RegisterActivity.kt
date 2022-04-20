package com.example.tugasramadhanretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.tugasramadhanretrofit.databinding.ActivityRegisterBinding
import com.example.tugasramadhanretrofit.model.RegisterRequest
import com.example.tugasramadhanretrofit.model.RegisterResponse
import com.example.tugasramadhanretrofit.service.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
        setupViews()
    }

    private fun setupViews() {
        binding.btnRegister.setOnClickListener{
            if (!binding.etEmail.text.isNullOrEmpty() && !binding.etPassword.text.isNullOrEmpty()){
                binding.pbLoading.visibility = View.VISIBLE
                actionRegistration(binding.etEmail.text.toString(), binding.etPassword.text.toString(),binding.etUsername.text.toString())
            } else {
                Toast.makeText(this, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun actionRegistration(email: String, pass: String,username:String) {
        val request = RegisterRequest(email, pass, username)
        ApiClient.instance.register(request).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                when (response.code()) {
                    201 -> {
                        binding.pbLoading.visibility = View.GONE
                        Toast.makeText(
                            this@RegisterActivity,
                            "Registrasi Berhasil!",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else -> {
                        binding.pbLoading.visibility = View.GONE
                        Toast.makeText(this@RegisterActivity, "Registrasi Gagal!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(this@RegisterActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}