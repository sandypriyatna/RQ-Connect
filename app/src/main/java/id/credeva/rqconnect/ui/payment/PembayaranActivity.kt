package id.credeva.rqconnect.ui.payment

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
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.data.adapter.payment.PaidOffAdapter
import id.credeva.rqconnect.data.db.entities.payment.PaidOff
import id.credeva.rqconnect.databinding.ActivityPembayaranBinding
import id.credeva.rqconnect.ui.payment.evidence.BuktiPembayaranActivity
import id.credeva.rqconnect.ui.payment.paymentSelection.PilihPembayaranActivity
import id.credeva.rqconnect.util.toast
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
        prefManager.spStatusPayment = null

        viewModel.remain.observe(this, Observer { remain ->
            try {
                cv_progress_payment.visibility = View.GONE
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

                prefManager.spPaymentId = remain[0].id.toString()
                prefManager.spRefKey = remain[0].refKey
                prefManager.spSppTotal = remain[0].price!!.toInt()
                prefManager.spDueDatePay = remain[0].validBefore
                prefManager.spEvidence = remain[0].evidence.toString()

                val statusPayment = remain[0].evidence

                if (statusPayment == null) {
                    btn_payment.text = "Bayar"
                    tv_month.setTextColor(Color.parseColor("#FF000000"))
                    tv_due_date.setTextColor(Color.parseColor("#FFFF4444"))
                    cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
                    btn_payment.visibility = View.VISIBLE
                } else if (statusPayment != null) {
                    btn_payment.text = "Lihat Bukti"
                    tv_month.setTextColor(Color.parseColor("#FF000000"))
                    tv_due_date.setTextColor(Color.parseColor("#FFFF4444"))
                    cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
                    btn_payment.visibility = View.VISIBLE
                    prefManager.spStatusPayment = "active"
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

        btn_payment.setOnClickListener {
            if (btn_payment.text.equals("Bayar")) {
                startActivity(Intent(this, PilihPembayaranActivity::class.java))
            } else {
                startActivity(Intent(this, BuktiPembayaranActivity::class.java))
            }
        }

        swipe_payment.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )

        swipe_payment.setColorSchemeColors(Color.WHITE)

        swipe_payment.setOnRefreshListener {
            viewModel.getRemain()
            viewModel.remain.observe(this, Observer { remain ->
                try {
                    cv_progress_payment.visibility = View.GONE
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
                    tv_due_date.text =
                        "Tenggat pembayaran ${dateString} ${monthString} ${yearString}"

                    prefManager.spPaymentId = remain[0].id.toString()
                    prefManager.spRefKey = remain[0].refKey
                    prefManager.spSppTotal = remain[0].price!!.toInt()
                    prefManager.spDueDatePay = remain[0].validBefore
                    prefManager.spEvidence = remain[0].evidence.toString()

                    val statusPayment = remain[0].evidence

                    if (statusPayment == null) {
                        btn_payment.text = "Bayar"
                        tv_month.setTextColor(Color.parseColor("#FF000000"))
                        tv_due_date.setTextColor(Color.parseColor("#FFFF4444"))
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
                        btn_payment.visibility = View.VISIBLE
                    } else if (statusPayment != null) {
                        btn_payment.text = "Lihat Bukti"
                        tv_month.setTextColor(Color.parseColor("#FF000000"))
                        tv_due_date.setTextColor(Color.parseColor("#FFFF4444"))
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
                        btn_payment.visibility = View.VISIBLE
                        prefManager.spStatusPayment = "active"
                    }

                } catch (e: Exception) {
                    Log.e("errorPembayaran", e.message.toString())
                }
            })
            swipe_payment.isRefreshing = false
        }

        swipe_history_payment.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )

        swipe_history_payment.setColorSchemeColors(Color.WHITE)

        swipe_history_payment.setOnRefreshListener {
            viewModel.getPaidOff()
            viewModel.paid.observe(this, Observer { paid ->
                rv_paid_off.also {
                    it.layoutManager = LinearLayoutManager(this)
                    it.setHasFixedSize(true)
                    it.adapter = PaidOffAdapter(paid, this)
                }
            })

            swipe_history_payment.isRefreshing = false
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

        dialogView.tv_ref_key.text = "Kode Pembayaran : #${paidOff.ref_key} "
        dialogView.tv_payment_total.text = "Total Pembayaran : Rp. ${paidOff.price} "

        dialogView.buttonOk.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}
