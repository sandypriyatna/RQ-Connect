package id.credeva.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import id.credeva.rqconnect.data.db.entities.Triwulan
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest

class TriwulanRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val triwulan = MutableLiveData<List<Triwulan>>()

    suspend fun getTriwulan() = apiRequest { api.getTriwulan() }

}