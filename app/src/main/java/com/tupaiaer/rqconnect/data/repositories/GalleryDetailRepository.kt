package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.GalleryDetail
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest
import com.tupaiaer.rqconnect.data.preferences.PrefManager

class GalleryDetailRepository(
    private val api: MyApi,
    private val pref: PrefManager
) : SafeApiRequest() {

    private val bank = MutableLiveData<List<GalleryDetail>>()

    suspend fun getGalleryDetail() = apiRequest { api.getDetailGallery(pref.spIdGallery) }

}