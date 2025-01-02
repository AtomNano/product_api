package com.fanntt.crud_berita_user.screen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fanntt.crud_berita_user.MainActivity
import com.fanntt.crud_berita_user.R
import com.fanntt.crud_berita_user.model.loginresspone
import com.fanntt.crud_berita_user.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_login)

        val etusername = findViewById<TextView>(R.id.etusername)
        val etpassword = findViewById<TextView>(R.id.etpassword)
        val txttoregister = findViewById<TextView>(R.id.txttoregister)
        val btnlogin = findViewById<TextView>(R.id.btnLogin)

        txttoregister.setOnClickListener() {
            val intent = Intent(this@LoginActivity, RegiterScreen::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener() {
            val username = etusername.text.toString()
            val password = etpassword.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this,
                    "username atau password tidak boleh kosong",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else {
                ApiClient.retrofit.loginUser(username, password).enqueue(object :
                    Callback<loginresspone> {

                    override fun onResponse(
                        call: Call<loginresspone>,
                        response: Response<loginresspone>
                    ) {
                        if (response.isSuccessful) {
                            val loginResponse = response.body()
                            if (loginResponse != null && response.isSuccessful) {
//                                Login Berhasil
                                Toast.makeText(
                                    this@LoginActivity, "Login Berhasil", Toast.LENGTH_SHORT
                                ).show()
                                val toMainActivity =
                                    Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(toMainActivity)
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginActivity, "Login Gagal", Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            val errorMessage =
                                response.errorBody()?.toString() ?: "Terjadi Kesalahan"
                            Log.e("Login Error", errorMessage)
                            Toast.makeText(this@LoginActivity, errorMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<loginresspone>, t: Throwable) {
                        Toast.makeText(
                            this@LoginActivity, t.message, Toast.LENGTH_SHORT
                        ).show()
                    }

                })

            }


        }
    }
}