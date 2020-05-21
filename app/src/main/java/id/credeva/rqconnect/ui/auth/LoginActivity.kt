package id.credeva.rqconnect.ui.auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.credeva.rqconnect.ui.main.MainActivity
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.data.db.entities.User
import id.credeva.rqconnect.databinding.ActivityLoginBinding
import id.credeva.rqconnect.util.gone
import id.credeva.rqconnect.util.show
import id.credeva.rqconnect.util.snackbar
import id.credeva.rqconnect.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel
        viewModel.authListener = this

        prefManager.spCheckIntroSlider = true

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (!prefManager.spName.isNullOrEmpty()) {
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

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity()
        }
        this.doubleBackToExitPressedOnce = true
        login_layout.snackbar("Ketuk sekali lagi untuk keluar")
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}
