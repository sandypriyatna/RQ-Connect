package id.credeva.rqconnect.ui.payment.checkout

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.ui.main.MainActivity
import id.credeva.rqconnect.ui.payment.paymentConfirmation.ConfirmActivity
import id.credeva.rqconnect.util.toast
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        getCheckoutData()

        btn_confirm.setOnClickListener {
            startActivity(Intent(this@CheckoutActivity, ConfirmActivity::class.java))
        }

        btn_later_confirm.setOnClickListener {
            startActivity(Intent(this@CheckoutActivity, MainActivity::class.java))
        }

        tv_copy.setOnClickListener {
            val textToCopy = tv_rek_number.text
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("rek", textToCopy)
            clipboardManager.setPrimaryClip(clipData)

            toast("Nomor rekening berhasil di copy")
        }

        iv_back.setOnClickListener { onBackPressed() }
    }

    private fun getCheckoutData() {
        tv_payment_code.text = prefManager.spRefKey
        tv_total_payment.text = prefManager.spTotalPayment
        tv_due_date.text = prefManager.spDueDatePay
        tv_rek.text = prefManager.spRek
        tv_rek_name.text = prefManager.spRekName
        tv_rek_number.text = prefManager.spRekNumber.toString()
    }

}
