package com.tupaiaer.rqconnect.data.repositories

import com.tupaiaer.rqconnect.data.db.AppDatabase
import com.tupaiaer.rqconnect.data.db.entities.User
import com.tupaiaer.rqconnect.data.network.MyApi
import com.tupaiaer.rqconnect.data.network.SafeApiRequest
import com.tupaiaer.rqconnect.data.network.responses.AuthResponse
import com.tupaiaer.rqconnect.data.preferences.PrefManager

class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val pref: PrefManager
) : SafeApiRequest() {

    suspend fun userLogin(nis: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(nis, password) }
    }

    suspend fun saveUser(user: User) {
        db.getUserDao().upsert(user)
        pref.spToken = user.token
    }

    fun getUser() = db.getUserDao().getUser()
}