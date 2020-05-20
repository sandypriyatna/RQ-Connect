package id.credeva.rqconnect.ui.payment.paymentSelection

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.data.adapter.ChosePaymentAdapter
import id.credeva.rqconnect.data.db.entities.Bank
import id.credeva.rqconnect.databinding.ActivityPilihPembayaranBinding
import id.credeva.rqconnect.ui.payment.checkout.CheckoutActivity
import id.credeva.rqconnect.ui.payment.checkout.OptionCheckoutActivity
import kotlinx.android.synthetic.main.activity_pilih_pembayaran.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class PilihPembayaranActivity : AppCompatActivity(), KodeinAware,
    PilihPembayaranViewClickListener {

    override val kodein by kodein()
    private val factory: PilihPembayaranViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityPilihPembayaranBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_pilih_pembayaran)
        val viewModel =
            ViewModelProviders.of(this, factory).get(PilihPembayaranViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.getChosePayment()
        viewModel.chosePayment.observe(this, Observer { bank ->
            rv_bank.also {
                it.layoutManager = LinearLayoutManager(this)
                it.setHasFixedSize(true)
                it.adapter = ChosePaymentAdapter(bank, this)
            }
        })

        iv_back.setOnClickListener { onBackPressed() }

    }

    override fun onBankViewClickListener(view: View, bank: Bank) {
        view.setOnClickListener {
            prefManager.spRek = bank.provider
            prefManager.spRekName = bank.name
            prefManager.spRekNumber = bank.number
            startActivity(Intent(this, OptionCheckoutActivity::class.java))
        }
    }
}
