package com.fanntt.crud_berita_user.screen

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fanntt.crud_berita_user.MainActivity
import com.fanntt.crud_berita_user.R
import com.fanntt.crud_berita_user.model.registerresponse
import com.fanntt.crud_berita_user.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegiterScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_regiter_screen)


        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etFullname = findViewById<EditText>(R.id.etFullname)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
//        set Btn

        btnRegister.setOnClickListener {
            val username = etUsername.text.toString()
            val fullname = etFullname.text.toString()
            val password = etPassword.text.toString()
            val email = etEmail.text.toString()

            try {
                ApiClient.retrofit.registerUser(
                    username,password,fullname,email
                ).enqueue(object : Callback<registerresponse> {
                    override fun onResponse(
                        call: Call<registerresponse>,
                        response: Response<registerresponse>
                    ){
                        if (response.isSuccessful){

                            Toast.makeText(this@RegiterScreen,response.body()?.message,Toast.LENGTH_SHORT).show()

                            val toMainActivity = Intent(this@RegiterScreen, listDataUser::class.java)
                            startActivity(toMainActivity)
                            finish()

                        }else{
                            Toast.makeText(this@RegiterScreen,"Register Error",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<registerresponse>, t: Throwable) {
                        Toast.makeText(this@RegiterScreen,t.message,Toast.LENGTH_SHORT).show()
                    }
                })
            } catch (e: Exception) {
                Toast.makeText(
                    this@RegiterScreen,
                    "Error occured: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.e(TAG, "Error: ${e.message}",e)
            }
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}