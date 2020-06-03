package id.credeva.rqconnect.data.network.responses
import com.google.gson.annotations.SerializedName


data class ConfirmationResponse(
    @SerializedName("exception")
    val exception: String?,
    @SerializedName("file")
    val `file`: String?,
    @SerializedName("line")
    val line: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("trace")
    val trace: List<Trace>?
)

data class Trace(
    @SerializedName("class")
    val classX: String?,
    @SerializedName("file")
    val `file`: String?,
    @SerializedName("function")
    val function: String?,
    @SerializedName("line")
    val line: Int?,
    @SerializedName("type")
    val type: String?
)