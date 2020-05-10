package com.tupaiaer.rqconnect.ui.tahfidz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bumptech.glide.Glide
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.RqconnectApplication.Companion.prefManager
import com.tupaiaer.rqconnect.ui.tahfidz.lajnah.LajnahFragment
import com.tupaiaer.rqconnect.ui.tahfidz.pekan.PekanFragment
import com.tupaiaer.rqconnect.ui.tahfidz.triwulan.TriwulanFragment
import kotlinx.android.synthetic.main.activity_tahfidz.*

class TahfidzActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tahfidz)

        val adapter = TabAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPager.adapter = adapter

        tabLayout.setupWithViewPager(viewPager)
        getTahfidzData()

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
