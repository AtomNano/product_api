package com.fanntt.crud_berita_user

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.fanntt.crud_berita_user.model.ModelBerita

class DetailPageProduct : AppCompatActivity() {

    private lateinit var gambar: ImageView
    private lateinit var judul : TextView
    private lateinit var isi_berirta : TextView
    private lateinit var tgl_berita : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_page_product)


//        Hubungkan dengan view
        gambar = findViewById(R.id.gambar)
        judul = findViewById(R.id.txtjudul)
        isi_berirta = findViewById(R.id.txtisi_berirta)
        tgl_berita = findViewById(R.id.txttglberita)

    val berita =intent.getSerializableExtra("berita") as ModelBerita

        berita?.let {
            judul.text = it.judul
            isi_berirta.text = it.isi_berirta
            tgl_berita.text = it.tgl_berita

            Glide.with(this).load("http://192.168.100.88/beritaDb/gambar_berita/"
                    + berita.gambar_berita).centerCrop().into(gambar)

        }


    }
}