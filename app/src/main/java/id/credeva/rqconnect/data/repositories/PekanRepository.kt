package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.Pekan
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest

class PekanRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val pekan = MutableLiveData<List<Pekan>>()

    suspend fun getPekan() = apiRequest { api.getPekan() }

}