package com.fanntt.crud_berita_user.model

import androidx.core.app.NotificationCompat.MessagingStyle.Message

data class registerresponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<modeluser>
)
