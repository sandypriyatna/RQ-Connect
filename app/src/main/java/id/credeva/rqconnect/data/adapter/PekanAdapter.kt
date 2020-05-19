package id.credeva.rqconnect.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.credeva.rqconnect.R
import id.credeva.rqconnect.data.db.entities.Pekan
import id.credeva.rqconnect.databinding.ItemPekanBinding

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