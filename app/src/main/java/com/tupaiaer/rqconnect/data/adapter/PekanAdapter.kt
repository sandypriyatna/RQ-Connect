package com.tupaiaer.rqconnect.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.data.db.entities.Pekan
import com.tupaiaer.rqconnect.databinding.ItemPekanBinding

class PekanAdapter(
    private val pekan: List<Pekan>
) : RecyclerView.Adapter<PekanAdapter.PekanViewHolder>() {

    override fun getItemCount() = pekan.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PekanViewHolder(
        DataBindingUtil.inflate<ItemPekanBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_pekan,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PekanViewHolder, position: Int) {
        holder.itemPekanBinding.pekan = pekan[position]
    }

    inner class PekanViewHolder(
        val itemPekanBinding: ItemPekanBinding
    ) : RecyclerView.ViewHolder(itemPekanBinding.root)

}