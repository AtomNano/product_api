package com.fanntt.crud_berita_user.response

import com.fanntt.crud_berita_user.model.modeluser

data class userresponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<modeluser>
)
