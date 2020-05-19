package id.credeva.rqconnect.data.db.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Bank(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("provider")
    val provider: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("path")
    val path: String?
)