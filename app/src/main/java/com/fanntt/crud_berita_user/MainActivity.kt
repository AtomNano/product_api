package com.fanntt.crud_berita_user

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.fanntt.crud_berita_user.adapter.BeritaAdapter
import com.fanntt.crud_berita_user.model.ModelBerita
import com.fanntt.crud_berita_user.response.ResponseBerita
import com.fanntt.crud_berita_user.screen.RegiterScreen
import com.fanntt.crud_berita_user.screen.listDataUser
import com.fanntt.crud_berita_user.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var beritaAdapter: BeritaAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ResponseBerita>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        swipeRefreshLayout = findViewById(R.id.refresh_layout)
        recyclerView = findViewById(R.id.rv_berita)
        beritaAdapter = BeritaAdapter { berita -> beritaOnClik(berita) }
        recyclerView.adapter = beritaAdapter
        recyclerView.layoutManager = LinearLayoutManager(
            applicationContext,LinearLayoutManager.VERTICAL,false
        )
        swipeRefreshLayout.setOnRefreshListener {
            getData()
        }
        getData()

        findViewById<Button>(R.id.btnTambahUser).setOnClickListener {
            val intent = Intent(this, RegiterScreen::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnLiatDataUser).setOnClickListener {
            val intent = Intent(this, listDataUser::class.java)
            startActivity(intent)
        }

    }


    private fun beritaOnClik(modelBerita: ModelBerita) {
        Toast.makeText(applicationContext,modelBerita.judul, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, DetailPageProduct::class.java).apply { putExtra("berita", modelBerita) }
        startActivity(intent)
    }

    private fun getData() {
        swipeRefreshLayout.isRefreshing = true
        call= ApiClient.retrofit.getAllBerita()
        call.enqueue(object : Callback<ResponseBerita> {
            override fun onResponse(
                call: Call<ResponseBerita>,
                response: Response<ResponseBerita>
            ) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        beritaAdapter.submitList(data.data)
                    }
                }

            }

            override fun onFailure(call: Call<ResponseBerita>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}