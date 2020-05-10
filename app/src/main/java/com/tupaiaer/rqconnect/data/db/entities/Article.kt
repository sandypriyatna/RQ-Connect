package com.tupaiaer.rqconnect.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Article(
    @SerializedName("author")
    var author: String?,
    @SerializedName("banner")
    var banner: String?,
    @SerializedName("blog")
    val blog: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    var title: String?
)