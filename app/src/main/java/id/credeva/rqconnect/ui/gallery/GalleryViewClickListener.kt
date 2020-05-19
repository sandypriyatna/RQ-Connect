package id.credeva.rqconnect.ui.gallery

import android.view.View
import id.credeva.rqconnect.data.db.entities.Gallery

interface GalleryViewClickListener {
    fun onGalleryViewClickListener(view: View, gallery: Gallery)
}