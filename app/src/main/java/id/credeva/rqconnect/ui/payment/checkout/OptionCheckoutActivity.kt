package id.credeva.rqconnect.ui.payment.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.util.toast
import kotlinx.android.synthetic.main.activity_option_checkout.*

class OptionCheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_checkout)

        btn_next_payment.setOnClickListener {
            if (et_tabungan.text.toString().trim().isEmpty() && et_infaq.text.toString().trim()
                    .isEmpty()
            ) {
                prefManager.spDeposit = 0
                prefManager.spInfaq = 0
                prefManager.spTotalPayment =
                    prefManager.spDeposit!! + prefManager.spInfaq!! + prefManager.spSppTotal!!
            } else if (et_tabungan.text.toString().trim().isEmpty()) {
                prefManager.spDeposit = 0
                prefManager.spInfaq = et_infaq.text.toString().toInt()
                prefManager.spTotalPayment =
                    prefManager.spDeposit!! + prefManager.spInfaq!! + prefManager.spSppTotal!!
            } else if (et_infaq.text.toString().trim().isEmpty()) {
                prefManager.spDeposit = et_tabungan.text.toString().toInt()
                prefManager.spInfaq = 0
                prefManager.spTotalPayment =
                    prefManager.spDeposit!! + prefManager.spInfaq!! + prefManager.spSppTotal!!
            } else {
                prefManager.spDeposit = et_tabungan.text.toString().toInt()
                prefManager.spInfaq = et_infaq.text.toString().toInt()
                prefManager.spTotalPayment =
                    prefManager.spDeposit!! + prefManager.spInfaq!! + prefManager.spSppTotal!!
            }
            startActivity(Intent(this@OptionCheckoutActivity, CheckoutActivity::class.java))
        }
    }
}
