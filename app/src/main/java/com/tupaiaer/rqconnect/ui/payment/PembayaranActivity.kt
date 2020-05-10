package com.tupaiaer.rqconnect.ui.payment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.RqconnectApplication.Companion.prefManager
import com.tupaiaer.rqconnect.data.adapter.payment.PaidOffAdapter
import com.tupaiaer.rqconnect.data.db.entities.payment.PaidOff
import com.tupaiaer.rqconnect.databinding.ActivityPembayaranBinding
import com.tupaiaer.rqconnect.ui.payment.evidence.BuktiPembayaranActivity
import com.tupaiaer.rqconnect.ui.payment.paymentSelection.PilihPembayaranActivity
import kotlinx.android.synthetic.main.activity_pembayaran.*
import kotlinx.android.synthetic.main.payment_dialog.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*

class PembayaranActivity : AppCompatActivity(), KodeinAware, PaidViewClickListener {

    override val kodein by kodein()
    private val factory: PembayaranViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityPembayaranBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_pembayaran)
        val viewModel = ViewModelProviders.of(this, factory).get(PembayaranViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.getRemain()
        viewModel.getPaidOff()

        viewModel.remain.observe(this, Observer { remain ->
            try {
                tv_total_payment.text = "Rp. " + remain[0].price
                tv_due_date.text = remain[0].validBefore
                tv_ref_key.text = remain[0].refKey

                val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date: Date = format.parse(remain[0].validBefore.toString())

                val dateString =
                    DateFormat.format("dd", date) as String
                val monthString =
                    DateFormat.format("MMM", date) as String
                val yearString =
                    DateFormat.format("yyyy", date) as String

                tv_month.text = "Pembayaran bulan ${monthString} "
                tv_due_date.text = "Tenggat pembayaran ${dateString} ${monthString} ${yearString}"

                prefManager.spIdPayment = remain[0].id.toString()
                prefManager.spRefKey = remain[0].refKey
                prefManager.spTotalPayment = remain[0].price
                prefManager.spDueDatePay = remain[0].validBefore
                prefManager.spEvidence = remain[0].evidence

                if (remain[0].evidence == null) {
                    btn_payment.text = "Bayar"
                } else {
                    btn_payment.text = "Lihat Bukti"
                }

            } catch (e: Exception) {
                Log.e("errorPembayaran", e.message.toString())
            }
        })

        viewModel.paid.observe(this, Observer { paid ->
            rv_paid_off.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = PaidOffAdapter(paid, this)
            }
        })

        when (tv_ref_key.text) {
            "-" -> {
                tv_month.text = "Lunas"
                cardView.setBackgroundColor(Color.parseColor("#F4FAF6"))
                tv_month.setTextColor(Color.parseColor("#219653"))
                tv_due_date.setTextColor(Color.parseColor("#219653"))
                tv_due_date.text = "Alhamdulillah SPP sudah terbayar"
                tv_total_payment.text = "Jazaakumullahu khairan"
                btn_payment.visibility = View.GONE
            }
        }

        btn_payment.setOnClickListener {
            if (btn_payment.text.equals("Bayar")) {
                startActivity(Intent(this, PilihPembayaranActivity::class.java))
            } else {
                startActivity(Intent(this, BuktiPembayaranActivity::class.java))
            }
        }

        iv_back.setOnClickListener { onBackPressed() }
    }

    override fun onClickItem(view: View, paidOff: PaidOff) {
        val viewGroup = findViewById<ViewGroup>(R.id.content)
        val dialogView: View =
            LayoutInflater.from(this).inflate(R.layout.payment_dialog, viewGroup, false)
        val builder =
            AlertDialog.Builder(this)

        builder.setView(dialogView)

        val alertDialog = builder.create()
        alertDialog.show()

        dialogView.tv_ref_key.text = "Kode Pembayaran : #${prefManager.spTempRefKey} "
        dialogView.tv_payment_total.text = "Total Pembayaran : Rp. ${prefManager.spTempPayTotal} "

        dialogView.buttonOk.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
