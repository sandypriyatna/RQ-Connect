package id.credeva.rqconnect.ui.introslider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import id.credeva.rqconnect.data.adapter.IntroSliderAdapter
import id.credeva.rqconnect.R
import id.credeva.rqconnect.ui.auth.LoginActivity
import kotlinx.android.synthetic.main.activity_intro_slider.*

class IntroSliderActivity : AppCompatActivity() {

    private var introSliderAdapter =
        IntroSliderAdapter(
            listOf(
                IntroSlider(
                    "Pembayaran",
                    "\nMelakukan pembayaran bulanan dengan mudah dan melihat riwayat pembayaran Anda",
                    R.drawable.ic_pembayaran
                ),
                IntroSlider(
                    "Tahfidz",
                    "\nMemantau progres perkembangan tahfidz/hafalan santri secara langsung",
                    R.drawable.ic_tahfidz
                ),
                IntroSlider(
                    "Artikel",
                    "\nMembaca artikel-artikel bermanfaat dari Pondok Pesantren",
                    R.drawable.ic_blog
                ),
                IntroSlider(
                    "Galeri",
                    "\nMelihat dokumentasi aktivitas dan kegiatan Pondok Pesantren",
                    R.drawable.ic_galeri
                )
            )
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_slider)

        setupIndicator()
        setCurrentIndicator(0)

        intro_slider_view.adapter = introSliderAdapter
        intro_slider_view.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        }
        )

        btn_next.setOnClickListener {
            if (intro_slider_view.currentItem + 1 < introSliderAdapter.itemCount) {
                intro_slider_view.currentItem += 1
            } else {
                startActivity(Intent(this@IntroSliderActivity, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun setupIndicator() {

        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            indicators_contaienr.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicators_contaienr.childCount
        for (i in 0 until childCount) {
            val imageView = indicators_contaienr[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}
