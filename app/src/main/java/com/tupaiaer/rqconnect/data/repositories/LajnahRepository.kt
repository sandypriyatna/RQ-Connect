package com.tupaiaer.rqconnect.data.repositories

import androidx.lifecycle.MutableLiveData
import com.tupaiaer.rqconnect.data.db.entities.Lajnah
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest

class LajnahRepository(
    private val api: MyApi
) : SafeApiRequest() {

    private val lajnah = MutableLiveData<List<Lajnah>>()

    suspend fun getLajnah() = apiRequest { api.getLajnah() }

}