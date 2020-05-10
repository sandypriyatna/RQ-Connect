package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.Triwulan
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest

class TriwulanRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val triwulan = MutableLiveData<List<Triwulan>>()

    suspend fun getTriwulan() = apiRequest { api.getTriwulan() }

}