package com.tupaiaer.rqconnect.data.db.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class GalleryDetail(
    @SerializedName("author")
    val author: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("path")
    val path: String
)