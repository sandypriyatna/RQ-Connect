package com.tupaiaer.rqconnect.data.adapter.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.RqconnectApplication.Companion.prefManager
import com.tupaiaer.rqconnect.data.db.entities.payment.PaidOff
import com.tupaiaer.rqconnect.databinding.ItemPaidOffBinding
import com.tupaiaer.rqconnect.ui.payment.PaidViewClickListener

class PaidOffAdapter(
    private val paidOff: List<PaidOff>,
    private val listener: PaidViewClickListener
) : RecyclerView.Adapter<PaidOffAdapter.PaidOffViewHolder>() {

    override fun getItemCount() = paidOff.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PaidOffViewHolder(
        DataBindingUtil.inflate<ItemPaidOffBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_paid_off,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PaidOffViewHolder, position: Int) {
        holder.itemPaidOffBinding.paidoff = paidOff[position]
        holder.itemPaidOffBinding.root.setOnClickListener {
            listener.onClickItem(holder.itemPaidOffBinding.root, paidOff[position])
            prefManager.spTempRefKey = paidOff[position].ref_key
            prefManager.spTempPayTotal = paidOff[position].price
        }
    }

    inner class PaidOffViewHolder(
        val itemPaidOffBinding: ItemPaidOffBinding
    ) : RecyclerView.ViewHolder(itemPaidOffBinding.root)

}