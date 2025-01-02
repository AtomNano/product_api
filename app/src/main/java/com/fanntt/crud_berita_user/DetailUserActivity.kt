package com.fanntt.crud_berita_user

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fanntt.crud_berita_user.model.modeluser
import com.fanntt.crud_berita_user.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user)

        // Ambil ID pengguna dari Intent
        val id = intent.getIntExtra("id", -1)
        if (id == -1) {
            Toast.makeText(this, "ID tidak ditemukan", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Referensi ke Views
        val tvUsername = findViewById<TextView>(R.id.tvDetailUsernameValue)
        val tvFullName = findViewById<TextView>(R.id.tvDetailFullNameValue)
        val tvEmail = findViewById<TextView>(R.id.tvDetailEmailValue)
        val tvTanggalDaftar = findViewById<TextView>(R.id.tvDetailTanggalDaftarValue)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Tombol kembali
        btnBack.setOnClickListener {
            finish()
        }

        // Panggil API untuk mendapatkan detail pengguna
        val apiService = ApiClient.retrofit
        apiService.getUserById(id).enqueue(object : Callback<modeluser> {
            override fun onResponse(call: Call<modeluser>, response: Response<modeluser>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        tvUsername.text = user.username
                        tvFullName.text = user.fullname
                        tvEmail.text = user.email
                        tvTanggalDaftar.text = user.tgl_daftar
                    } else {
                        Toast.makeText(this@DetailUserActivity, "User tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@DetailUserActivity, "Gagal memuat data: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<modeluser>, t: Throwable) {
                Toast.makeText(this@DetailUserActivity, "Gagal memuat data: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
