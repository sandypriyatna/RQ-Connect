package id.credeva.rqconnect.data.db.entities.payment

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Late(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("evidence")
    val evidence: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("member_id")
    val memberId: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("ref_key")
    val refKey: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("valid_before")
    val validBefore: String
)

