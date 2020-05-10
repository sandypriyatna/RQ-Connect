package com.tupaiaer.rqconnect.data.network.responses

import com.google.gson.annotations.SerializedName
import com.tupaiaer.rqconnect.data.db.entities.GalleryDetail

data class GalleryDetailResponse(
    @SerializedName("data")
    val data: List<GalleryDetail>?
)