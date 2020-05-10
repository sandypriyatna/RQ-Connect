package com.tupaiaer.rqconnect.ui.profile

import androidx.lifecycle.ViewModel
import com.tupaiaer.rqconnect.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()
}
