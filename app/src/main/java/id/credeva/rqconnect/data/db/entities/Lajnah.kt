package id.credeva.rqconnect.data.db.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Lajnah(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("facilitator")
    val facilitator: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("juz")
    val juz: String?,
    @SerializedName("status")
    var status: String?
)
