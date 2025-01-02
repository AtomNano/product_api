package com.fanntt.crud_berita_user.screen

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fanntt.crud_berita_user.R
import com.fanntt.crud_berita_user.adapter.UserAdapter
import com.fanntt.crud_berita_user.model.modeluser
import com.fanntt.crud_berita_user.response.userresponse
import com.fanntt.crud_berita_user.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class listDataUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_data_user)

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rv_datauser)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Panggil API untuk mendapatkan data user
        val apiService = ApiClient.retrofit // Gunakan properti retrofit
        apiService.getUsers().enqueue(object : Callback<userresponse> {
            override fun onResponse(call: Call<userresponse>, response: Response<userresponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse != null && userResponse.isSuccess) {
                        val users = userResponse.data
                        recyclerView.adapter = UserAdapter(users)
                    } else {
                        Toast.makeText(this@listDataUser, "Tidak ada data.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@listDataUser, "Gagal memuat data: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<userresponse>, t: Throwable) {
                Toast.makeText(this@listDataUser, "Gagal memuat data: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


        // Handle insets untuk padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
