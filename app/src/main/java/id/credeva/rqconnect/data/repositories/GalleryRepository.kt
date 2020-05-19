package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.Gallery
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest

class GalleryRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val gallery = MutableLiveData<List<Gallery>>()

    suspend fun getGallery() = apiRequest { api.getGallery() }

}