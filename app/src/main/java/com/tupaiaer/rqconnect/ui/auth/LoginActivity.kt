package com.tupaiaer.rqconnect.ui.auth

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tupaiaer.rqconnect.ui.main.MainActivity
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.RqconnectApplication.Companion.prefManager
import com.tupaiaer.rqconnect.data.db.entities.User
import com.tupaiaer.rqconnect.data.preferences.PrefManager
import com.tupaiaer.rqconnect.databinding.ActivityLoginBinding
import com.tupaiaer.rqconnect.util.gone
import com.tupaiaer.rqconnect.util.show
import com.tupaiaer.rqconnect.util.snackbar
import com.tupaiaer.rqconnect.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

        prefManager.spCheckIntroSlider = true

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        materialButton.visibility = View.GONE
        progress_bar_login.show()
    }

    override fun onSuccess(user: User?) {
        progress_bar_login.gone()
        toast("Assalamu'alaikum ${user!!.name}")
        prefManager.spNis = user.nis
        prefManager.spName = user.name
        prefManager.spAvatar = user.avatar
        prefManager.spJuz = user.id.toString()
    }

    override fun onFailure(message: String) {
        progress_bar_login.gone()
        materialButton.visibility = View.VISIBLE
        login_layout.snackbar(message)
    }
}
