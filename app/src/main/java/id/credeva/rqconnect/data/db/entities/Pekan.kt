package id.credeva.rqconnect.data.db.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Pekan(
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("facilitator")
    val facilitator: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("page")
    val page: String?,
    @SerializedName("juz")
    val juz: String?,
    @SerializedName("juz_amount")
    val juz_amount: String?
)
