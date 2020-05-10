package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.Gallery
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest

class GalleryRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val gallery = MutableLiveData<List<Gallery>>()

    suspend fun getGallery() = apiRequest { api.getGallery() }

}