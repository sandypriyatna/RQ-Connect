package id.credeva.rqconnect.ui.auth

import id.credeva.rqconnect.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User?)
    fun onFailure(message: String)
}