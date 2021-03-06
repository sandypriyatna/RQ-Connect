package id.credeva.rqconnect.ui.auth

import android.accounts.NetworkErrorException
import android.view.View
import androidx.lifecycle.ViewModel
import id.credeva.rqconnect.data.repositories.UserRepository
import id.credeva.rqconnect.util.ApiException
import id.credeva.rqconnect.util.Coroutines

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var nis: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginClick(view: View) {
        authListener?.onStarted()
        if (nis.isNullOrEmpty() || password.isNullOrEmpty() || nis.toString() == "" || password.toString() == "") {
            authListener?.onFailure("Maaf,\nNIS atau password kosong")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(nis!!, password!!)
                authResponse.data?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.data?.name.toString())
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NetworkErrorException) {
                authListener?.onFailure(e.message!!)
            }
        }
    }
}