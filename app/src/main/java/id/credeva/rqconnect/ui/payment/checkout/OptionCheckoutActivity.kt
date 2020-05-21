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
                prefManager.spDeposit = "0"
                prefManager.spInfaq = "0"
                prefManager.spTotalPayment =
                    prefManager.spDeposit!! + prefManager.spInfaq!! + prefManager.spSppTotal.toString()
                        .toInt()
            } else if (et_tabungan.text.toString().trim().isEmpty()) {
                prefManager.spDeposit = "0"
                prefManager.spInfaq = et_infaq.text.toString()
                prefManager.spTotalPayment =
                    prefManager.spDeposit!! + prefManager.spInfaq!! + prefManager.spSppTotal.toString()
                        .toInt()
            } else if (et_infaq.text.toString().trim().isEmpty()) {
                prefManager.spDeposit = et_tabungan.text.toString()
                prefManager.spInfaq = "0"
                prefManager.spTotalPayment =
                    prefManager.spDeposit!! + prefManager.spInfaq!! + prefManager.spSppTotal.toString()
                        .toInt()
            } else {
                prefManager.spDeposit = et_tabungan.text.toString()
                prefManager.spInfaq = et_infaq.text.toString()
                prefManager.spTotalPayment =
                    prefManager.spDeposit!! + prefManager.spInfaq!! + prefManager.spSppTotal.toString()
                        .toInt()
            }
            startActivity(Intent(this@OptionCheckoutActivity, CheckoutActivity::class.java))
        }
    }
}
