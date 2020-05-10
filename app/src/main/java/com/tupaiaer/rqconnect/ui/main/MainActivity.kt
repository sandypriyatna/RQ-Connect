package com.tupaiaer.rqconnect.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.RqconnectApplication.Companion.prefManager
import com.tupaiaer.rqconnect.ui.article.ArticleActivity
import com.tupaiaer.rqconnect.ui.gallery.GalleryActivity
import com.tupaiaer.rqconnect.ui.payment.PembayaranActivity
import com.tupaiaer.rqconnect.ui.tahfidz.TahfidzActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_name.text = "Selamat datang, ${prefManager.spName} "

        if (prefManager.spEvidence == null || prefManager.spEvidence.toString() == null) {
            tv_payment_information.visibility = View.VISIBLE
        }

        cv_pembayaran.setOnClickListener {
            startActivity(Intent(this@MainActivity, PembayaranActivity::class.java))
        }

        cv_tahfidz.setOnClickListener {
            startActivity(Intent(this@MainActivity, TahfidzActivity::class.java))
        }

        cv_blog.setOnClickListener {
            startActivity(Intent(this@MainActivity, ArticleActivity::class.java))
        }

        cv_gallery.setOnClickListener {
            startActivity(Intent(this@MainActivity, GalleryActivity::class.java))
        }
    }
}
