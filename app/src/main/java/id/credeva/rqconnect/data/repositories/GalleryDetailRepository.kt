package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.GalleryDetail
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest
import id.credeva.rqconnect.data.preferences.PrefManager

class GalleryDetailRepository(
    private val api: MyApi,
    private val pref: PrefManager
) : SafeApiRequest() {

    private val galleryDetail = MutableLiveData<List<GalleryDetail>>()

    suspend fun getGalleryDetail() = apiRequest { api.getDetailGallery(pref.spGalleryId) }

}