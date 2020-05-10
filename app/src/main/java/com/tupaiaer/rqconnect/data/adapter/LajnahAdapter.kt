package com.tupaiaer.rqconnect.data.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.data.db.entities.Lajnah
import com.tupaiaer.rqconnect.databinding.ItemLajnahBinding

class LajnahAdapter(
    private val lajnah: List<Lajnah>
) : RecyclerView.Adapter<LajnahAdapter.LajnahViewHolder>() {

    override fun getItemCount() = lajnah.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LajnahViewHolder(
        DataBindingUtil.inflate<ItemLajnahBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_lajnah,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: LajnahViewHolder, position: Int) {
        holder.itemLajnahBinding.lajnah = lajnah[position]
        if (lajnah[position].status == "Belum Tuntas") {
            holder.itemLajnahBinding.tvStatus.setTextColor(Color.parseColor("#EB5757"))
            holder.itemLajnahBinding.cvJuz.setCardBackgroundColor(Color.parseColor("#FEF7F7"))
            holder.itemLajnahBinding.tvJuz.setTextColor(Color.parseColor("#EB5757"))
        } else {
            holder.itemLajnahBinding.tvStatus.setTextColor(Color.parseColor("#219653"))
            holder.itemLajnahBinding.cvJuz.setCardBackgroundColor(Color.parseColor("#F4FAF6"))
            holder.itemLajnahBinding.tvJuz.setTextColor(Color.parseColor("#219553"))
        }
    }

    inner class LajnahViewHolder(
        val itemLajnahBinding: ItemLajnahBinding
    ) : RecyclerView.ViewHolder(itemLajnahBinding.root)

}