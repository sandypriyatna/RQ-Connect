package id.credeva.rqconnect.ui.gallery

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.data.adapter.GalleryAdapter
import id.credeva.rqconnect.data.db.entities.Gallery
import id.credeva.rqconnect.databinding.ActivityGalleryBinding
import id.credeva.rqconnect.ui.gallery.detail.GalleryDetailActivity
import id.credeva.rqconnect.util.toast
import kotlinx.android.synthetic.main.activity_gallery.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.lang.Exception

class GalleryActivity : AppCompatActivity(), KodeinAware, GalleryViewClickListener {

    override val kodein by kodein()
    private val factory: GalleryViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityGalleryBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_gallery)
        val viewModel = ViewModelProviders.of(this, factory).get(GalleryViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.getGallery()
        viewModel.gallery.observe(this, Observer { gallery ->
            try {
                pb_gallery.visibility = View.GONE
                rv_gallery.also {
                    it.layoutManager = LinearLayoutManager(this)
                    it.setHasFixedSize(true)
                    it.adapter = GalleryAdapter(gallery, this)
                }
            } catch (e: Exception) {
                Log.v("errorGallery: ", e.message)
                pb_gallery.visibility = View.GONE
                toast("Tidak ada data gallery")
            }
        })

        itemsswipetorefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        itemsswipetorefresh.setColorSchemeColors(Color.WHITE)

        itemsswipetorefresh.setOnRefreshListener {
            viewModel.getGallery()
            viewModel.gallery.observe(this, Observer { gallery ->
                try {
                    pb_gallery.visibility = View.GONE
                    rv_gallery.also {
                        it.layoutManager = LinearLayoutManager(this)
                        it.adapter = GalleryAdapter(gallery, this)
                    }
                } catch (e: Exception) {
                    Log.v("errorGallery: ", e.message)
                    pb_gallery.visibility = View.GONE
                    toast("Tidak ada data gallery")
                }
            })

            itemsswipetorefresh.isRefreshing = false
        }

        iv_back.setOnClickListener { onBackPressed() }
    }

    override fun onGalleryViewClickListener(view: View, gallery: Gallery) {
        view.setOnClickListener {
            prefManager.spGalleryId = gallery.id!!
            startActivity(Intent(this@GalleryActivity, GalleryDetailActivity::class.java))
        }
    }
}
