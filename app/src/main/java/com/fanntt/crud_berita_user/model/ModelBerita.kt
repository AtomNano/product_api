package com.fanntt.crud_berita_user.model

import java.io.Serializable

data class ModelBerita(
    val id : Int,
    val judul : String,
    val isi_berirta : String,
    val gambar_berita : String,
    val tgl_berita : String
) : Serializable
