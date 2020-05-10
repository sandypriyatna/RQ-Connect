package com.tupaiaer.rqconnect.ui.auth

import com.tupaiaer.rqconnect.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user: User?)
    fun onFailure(message: String)
}