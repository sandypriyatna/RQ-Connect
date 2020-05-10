package com.tupaiaer.rqconnect.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.data.db.entities.GalleryDetail
import com.tupaiaer.rqconnect.databinding.ItemGalleryDetailBinding

class GalleryDetailAdapter(
    private val galleryDetail: List<GalleryDetail>
) : RecyclerView.Adapter<GalleryDetailAdapter.GalleryDetailViwHolder>() {

    override fun getItemCount() = galleryDetail.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GalleryDetailViwHolder(
        DataBindingUtil.inflate<ItemGalleryDetailBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_gallery_detail,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: GalleryDetailViwHolder, position: Int) {
        holder.itemGalleryBinding.gallerydetail = galleryDetail[position]
    }

    inner class GalleryDetailViwHolder(
        val itemGalleryBinding: ItemGalleryDetailBinding
    ) : RecyclerView.ViewHolder(itemGalleryBinding.root)

}