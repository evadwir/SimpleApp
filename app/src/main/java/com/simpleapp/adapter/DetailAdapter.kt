package com.simpleapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.simpleapp.R
import com.simpleapp.util.GlideApp

class DetailAdapter(): PagerAdapter() {

    private var totalItem: Int = 0
    private val MAX_ITEMS = 7
    var listItem: MutableList<Int> = mutableListOf()
        set(value) {
            field = value
            totalItem = value.size
            notifyDataSetChanged()
        }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_image, container, false)
        val image = view.findViewById<ImageView>(R.id.img_item)

        GlideApp.with(view)
                .load(listItem[getPosition(position)])
                .centerCrop()
                .into(image)
        container.addView(view)

        return view
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun getCount() = if (totalItem <= MAX_ITEMS) listItem.size else MAX_ITEMS

    fun getPosition(position: Int) = if (totalItem == 0) position else position % totalItem
}