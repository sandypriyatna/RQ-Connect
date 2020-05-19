package id.credeva.rqconnect.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.credeva.rqconnect.R
import id.credeva.rqconnect.data.db.entities.Bank
import id.credeva.rqconnect.databinding.ItemBankBinding
import id.credeva.rqconnect.ui.payment.paymentSelection.PilihPembayaranViewClickListener

class ChosePaymentAdapter(
    private val bank: List<Bank>,
    private val listener: PilihPembayaranViewClickListener
) : RecyclerView.Adapter<ChosePaymentAdapter.BankViewHolder>() {

    override fun getItemCount() = bank.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BankViewHolder(
        DataBindingUtil.inflate<ItemBankBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_bank,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        holder.itemBankBinding.bank = bank[position]
        holder.itemBankBinding.root.setOnClickListener {
            listener.onBankViewClickListener(holder.itemBankBinding.root, bank[position])
        }
    }

    inner class BankViewHolder(
        val itemBankBinding: ItemBankBinding
    ) : RecyclerView.ViewHolder(itemBankBinding.root)

}