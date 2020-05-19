package id.credeva.rqconnect.ui.payment.evidence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import kotlinx.android.synthetic.main.activity_bukti_pembayaran.*

class BuktiPembayaranActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bukti_pembayaran)

        getEvidenceData()

        iv_back.setOnClickListener { onBackPressed() }

    }

    private fun getEvidenceData() {
        tv_payment_code.text = prefManager.spRefKey
        Glide.with(this)
            .load(prefManager.spEvidence)
            .into(iv_evidence)
    }
}
