package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.Lajnah
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest

class LajnahRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val lajnah = MutableLiveData<List<Lajnah>>()

    suspend fun getLajnah() = apiRequest { api.getLajnah() }

}