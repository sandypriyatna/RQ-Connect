package id.credeva.rqconnect.ui.profile

import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.data.repositories.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()
}
