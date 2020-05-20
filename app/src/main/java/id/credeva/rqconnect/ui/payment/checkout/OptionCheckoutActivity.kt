package id.credeva.rqconnect.ui.payment.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import kotlinx.android.synthetic.main.activity_option_checkout.*

class OptionCheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_checkout)

        btn_next_payment.setOnClickListener {

            var deposit = et_tabungan.text.toString().toInt()
            var infaq = et_infaq.text.toString().toInt()

            if (deposit.toString().isEmpty()) {
                prefManager.spTabungan = 0
                prefManager.spSumTotalPayment = 500000
                startActivity(Intent(this@OptionCheckoutActivity, CheckoutActivity::class.java))
            } else if (infaq.toString().isEmpty()) {
                prefManager.spInfaq = 0
                prefManager.spSumTotalPayment = 500000
                startActivity(Intent(this@OptionCheckoutActivity, CheckoutActivity::class.java))
            } else {
                prefManager.spTabungan = deposit
                prefManager.spInfaq = infaq
                prefManager.spSumTotalPayment =
                    prefManager.spTabungan!! + prefManager.spInfaq!! + prefManager.spTotalPayment.toString()
                        .toInt()
                startActivity(Intent(this@OptionCheckoutActivity, CheckoutActivity::class.java))
            }
        }
    }
}
