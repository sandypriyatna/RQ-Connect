package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.Pekan
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest

class PekanRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val pekan = MutableLiveData<List<Pekan>>()

    suspend fun getPekan() = apiRequest { api.getPekan() }

}