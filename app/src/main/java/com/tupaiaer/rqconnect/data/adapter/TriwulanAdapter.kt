package com.tupaiaer.rqconnect.data.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.data.db.entities.Triwulan
import com.tupaiaer.rqconnect.databinding.ItemTriwulanBinding

class TriwulanAdapter(
    private val triwulan: List<Triwulan>
) : RecyclerView.Adapter<TriwulanAdapter.TriwulanViewHolder>() {

    override fun getItemCount() = triwulan.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TriwulanViewHolder(
        DataBindingUtil.inflate<ItemTriwulanBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_triwulan,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: TriwulanViewHolder, position: Int) {
        holder.itemTriwulanBinding.triwulan = triwulan[position]
        if (triwulan[position].status == "Belum Lulus") {
            holder.itemTriwulanBinding.tvStatus.setTextColor(Color.parseColor("#EB5757"))
            holder.itemTriwulanBinding.cvStatus.setCardBackgroundColor(Color.parseColor("#FEF7F7"))
            holder.itemTriwulanBinding.tvJuz.setTextColor(Color.parseColor("#EB5757"))
        } else {
            holder.itemTriwulanBinding.tvStatus.setTextColor(Color.parseColor("#219653"))
            holder.itemTriwulanBinding.cvStatus.setCardBackgroundColor(Color.parseColor("#F4FAF6"))
            holder.itemTriwulanBinding.tvJuz.setTextColor(Color.parseColor("#219553"))
        }
    }

    inner class TriwulanViewHolder(
        val itemTriwulanBinding: ItemTriwulanBinding
    ) : RecyclerView.ViewHolder(itemTriwulanBinding.root)

}