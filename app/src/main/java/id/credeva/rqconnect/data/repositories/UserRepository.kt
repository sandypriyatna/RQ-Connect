package id.credeva.rqconnect.data.repositories

import id.credeva.rqconnect.data.db.AppDatabase
import id.credeva.rqconnect.data.db.entities.User
import id.credeva.rqconnect.data.network.MyApi
import id.credeva.rqconnect.data.network.SafeApiRequest
import id.credeva.rqconnect.data.network.responses.AuthResponse
import id.credeva.rqconnect.data.preferences.PrefManager

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