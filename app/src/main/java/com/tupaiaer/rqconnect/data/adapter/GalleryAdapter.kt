package com.tupaiaer.rqconnect.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.data.db.entities.Gallery
import com.tupaiaer.rqconnect.databinding.ItemGalleryBinding
import com.tupaiaer.rqconnect.ui.gallery.GalleryViewClickListener

class GalleryAdapter(
    private val gallery: List<Gallery>,
    private val listener: GalleryViewClickListener
) : RecyclerView.Adapter<GalleryAdapter.GalleryViwHolder>() {

    override fun getItemCount() = gallery.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GalleryViwHolder(
        DataBindingUtil.inflate<ItemGalleryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_gallery,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: GalleryViwHolder, position: Int) {
        holder.itemGalleryBinding.gallery = gallery[position]
        holder.itemGalleryBinding.cvGaleri.setOnClickListener {
            listener.onGalleryViewClickListener(holder.itemGalleryBinding.root, gallery[position])
        }
    }

    inner class GalleryViwHolder(
        val itemGalleryBinding: ItemGalleryBinding
    ) : RecyclerView.ViewHolder(itemGalleryBinding.root)

}