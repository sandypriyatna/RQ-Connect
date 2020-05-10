package com.tupaiaer.rqconnect.ui.splashscreen

import android.content.Intent
import android.net.MailTo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.RqconnectApplication.Companion.prefManager
import com.tupaiaer.rqconnect.ui.auth.LoginActivity
import com.tupaiaer.rqconnect.ui.introslider.IntroSliderActivity
import com.tupaiaer.rqconnect.ui.main.MainActivity

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
