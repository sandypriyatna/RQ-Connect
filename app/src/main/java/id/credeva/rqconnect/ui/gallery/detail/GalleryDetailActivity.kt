package id.credeva.rqconnect.ui.gallery.detail

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.credeva.rqconnect.R
import id.credeva.rqconnect.data.adapter.GalleryAdapter
import id.credeva.rqconnect.data.adapter.GalleryDetailAdapter
import id.credeva.rqconnect.databinding.ActivityGalleryDetailBinding
import id.credeva.rqconnect.util.toast
import kotlinx.android.synthetic.main.activity_gallery_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.lang.Exception

class GalleryDetailActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: GalleryDetailViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityGalleryDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_gallery_detail)
        val viewModel = ViewModelProviders.of(this, factory).get(GalleryDetailViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.getGalleryDetail()
        viewModel.galleryDetail.observe(this, Observer { galleryDetail ->
            try {
                pb_gallery_detail.visibility = View.GONE
                rv_gallery_detail.also {
                    it.layoutManager = LinearLayoutManager(this)
                    it.setHasFixedSize(true)
                    it.adapter = GalleryDetailAdapter(galleryDetail)
                }
            } catch (e: Exception) {
                Log.v("errorDetailGallery: ", e.message)
            }
        })

        swipe_detail_gallery.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        swipe_detail_gallery.setColorSchemeColors(Color.WHITE)

        swipe_detail_gallery.setOnRefreshListener {
            viewModel.getGalleryDetail()
            viewModel.galleryDetail.observe(this, Observer { galleryDetail ->
                try {
                    pb_gallery_detail.visibility = View.GONE
                    rv_gallery_detail.also {
                        it.layoutManager = LinearLayoutManager(this)
                        it.setHasFixedSize(true)
                        it.adapter = GalleryDetailAdapter(galleryDetail)
                    }
                } catch (e: Exception) {
                    Log.v("errorDetailGallery: ", e.message)
                }
            })
            swipe_detail_gallery.isRefreshing = false
        }

        iv_back.setOnClickListener { onBackPressed() }

    }
}
