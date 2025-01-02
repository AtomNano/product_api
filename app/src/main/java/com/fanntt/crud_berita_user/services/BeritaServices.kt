package com.fanntt.crud_berita_user.services

import com.fanntt.crud_berita_user.model.loginresspone
import com.fanntt.crud_berita_user.model.modeluser
import com.fanntt.crud_berita_user.model.registerresponse
import com.fanntt.crud_berita_user.response.ResponseBerita
import com.fanntt.crud_berita_user.response.userresponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BeritaServices {

    @GET("getBerita.php")
    fun getAllBerita(): Call<ResponseBerita>


    @FormUrlEncoded
    @POST("register.php")
    fun registerUser(
        @Field("username") username : String,
        @Field("password") password : String,
        @Field("fullname") fullname : String,
        @Field("email") email : String,

        ):Call<registerresponse>

    @FormUrlEncoded
    @POST("login.php")
    fun loginUser(
        @Field("username") username : String,
        @Field("password") password : String,
        ):Call<loginresspone>


    @GET("getUser.php") // Sesuaikan endpoint API Anda
    fun getUsers(): Call<userresponse>

    @GET("getUserById.php")
    fun getUserById(@Query("id") id: Int): Call<modeluser>



}