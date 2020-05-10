package com.tupaiaer.rqconnect.data.db.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Gallery(
    @SerializedName("alternate")
    val alternate: String?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("cover")
    val cover: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("id")
    val id: Int?
)