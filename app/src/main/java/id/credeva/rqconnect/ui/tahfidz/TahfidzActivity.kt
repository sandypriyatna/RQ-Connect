package id.credeva.rqconnect.ui.tahfidz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.Glide
import id.credeva.rqconnect.R
import id.credeva.rqconnect.RqconnectApplication.Companion.prefManager
import id.credeva.rqconnect.ui.auth.LoginActivity
import id.credeva.rqconnect.ui.tahfidz.lajnah.LajnahFragment
import id.credeva.rqconnect.ui.tahfidz.pekan.PekanFragment
import id.credeva.rqconnect.ui.tahfidz.triwulan.TriwulanFragment
import kotlinx.android.synthetic.main.activity_tahfidz.*

class TahfidzActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tahfidz)

        val adapter = TabAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
        getTahfidzData()

        btn_logout.setOnClickListener {
            prefManager.clear()
            startActivity(Intent(this@TahfidzActivity, LoginActivity::class.java))
            finish()
        }

        iv_back.setOnClickListener { onBackPressed() }

    }

    private fun getTahfidzData() {
        tv_name.text = prefManager.spName
        tv_nis.text = "NIS : ${prefManager.spNis}"
        Glide.with(this)
            .load(prefManager.spAvatar)
            .into(iv_avatar)
    }

    class TabAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {
        private val tabName: Array<String> = arrayOf("Pekan", "Lajnah", "Triwulan")

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> PekanFragment()
            1 -> LajnahFragment()
            2 -> TriwulanFragment()
            else -> PekanFragment()
        }

        override fun getCount(): Int = 3
        override fun getPageTitle(position: Int): CharSequence? = tabName.get(position)
    }
}
