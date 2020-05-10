package com.tupaiaer.rqconnect.data.db.entities.payment
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Late(
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("evidence")
    val evidence: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("member_id")
    val member_id: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("ref_key")
    val ref_key: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updated_at: String,
    @SerializedName("valid_before")
    val valid_before: String
)

