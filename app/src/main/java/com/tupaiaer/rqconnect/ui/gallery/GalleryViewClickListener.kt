package com.tupaiaer.rqconnect.ui.gallery

import android.view.View
import com.tupaiaer.rqconnect.data.db.entities.Article
import com.tupaiaer.rqconnect.data.db.entities.Gallery

interface GalleryViewClickListener {
    fun onGalleryViewClickListener(view: View, gallery: Gallery)
}