package id.credeva.rqconnect.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_USER_ID = 0

@Entity
data class User(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("nis")
    var nis: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("ttl")
    var ttl: String? = null,
    @SerializedName("avatar")
    var avatar: String? = null,
    @SerializedName("token")
    var token: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("uid")
    var uid: Int = CURRENT_USER_ID
}