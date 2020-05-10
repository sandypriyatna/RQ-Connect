package com.tupaiaer.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tupaiaer.rqconnect.data.db.entities.Gallery

data class GalleryResponse(
    @SerializedName("data")
    val data: List<Gallery>?
)