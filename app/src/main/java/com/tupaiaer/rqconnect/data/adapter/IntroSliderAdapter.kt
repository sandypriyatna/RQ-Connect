package com.tupaiaer.rqconnect.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tupaiaer.rqconnect.R
import com.tupaiaer.rqconnect.ui.introslider.IntroSlider

class IntroSliderAdapter(private var introSlider: List<IntroSlider>) :
    RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder>() {

    inner class IntroSliderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title = view.findViewById<TextView>(R.id.tv_title_slider)
        private val description = view.findViewById<TextView>(R.id.tv_description_slider)
        private val image = view.findViewById<ImageView>(R.id.img_slider)

        fun bind(introSlider: IntroSlider) {
            title.text = introSlider.title
            description.text = introSlider.description
            image.setImageResource(introSlider.icon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSliderViewHolder {
        return IntroSliderViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.slide_item_container, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return introSlider.size
    }

    override fun onBindViewHolder(holder: IntroSliderViewHolder, position: Int) {
        holder.bind(introSlider[position])
    }
}