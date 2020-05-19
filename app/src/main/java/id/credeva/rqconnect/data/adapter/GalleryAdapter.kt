package id.credeva.rqconnect.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.credeva.rqconnect.R
import id.credeva.rqconnect.data.db.entities.Gallery
import id.credeva.rqconnect.databinding.ItemGalleryBinding
import id.credeva.rqconnect.ui.gallery.GalleryViewClickListener

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