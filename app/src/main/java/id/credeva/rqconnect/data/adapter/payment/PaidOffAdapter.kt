package id.credeva.rqconnect.data.adapter.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.credeva.rqconnect.R
import id.credeva.rqconnect.data.db.entities.payment.PaidOff
import id.credeva.rqconnect.databinding.ItemPaidOffBinding
import id.credeva.rqconnect.ui.payment.PaidViewClickListener

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
        }
    }

    inner class PaidOffViewHolder(
        val itemPaidOffBinding: ItemPaidOffBinding
    ) : RecyclerView.ViewHolder(itemPaidOffBinding.root)

}