package id.credeva.rqconnect.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.ui.auth.LoginActivity
import id.credeva.rqconnect.ui.introslider.IntroSliderActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            if (prefManager.spCheckIntroSlider) {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@SplashScreenActivity, IntroSliderActivity::class.java))
                finish()
            }
        }, 3000)
    }
}
