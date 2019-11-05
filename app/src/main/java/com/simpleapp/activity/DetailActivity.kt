package com.simpleapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.simpleapp.R
import com.simpleapp.adapter.DetailAdapter
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.util.*
import kotlin.collections.ArrayList

class DetailActivity : AppCompatActivity(), ViewPager.OnPageChangeListener  {

    private val requestType by lazy {
        intent.extras.get("data") as ArrayList<Int>
    }

    private val runnable: Runnable by lazy {
        Runnable {
            vp_detail?.setCurrentItem(if (vp_detail.currentItem == detailAdapter.count - 1) 0 else vp_detail.currentItem + 1, true)
        }
    }
    override fun onPageScrollStateChanged(state: Int) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            startAutoScroll()
        } else {
            timer?.cancel()
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        if (position == detailAdapter.listItem.size - 1) {
            Handler().postDelayed({
                detailAdapter.listItem.addAll(detailAdapter.listItem)
                detailAdapter.notifyDataSetChanged()
            }, DELAY_REFRESH)
        }

        pager_indicator?.setSelected(detailAdapter.getPosition(position))
    }

    private fun startAutoScroll() {
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, DELAY_TIME, DELAY_TIME)
    }
    private val handler = Handler()
    private val detailAdapter = DetailAdapter()
    private var timer: Timer? = null
    private val DELAY_TIME = 2000L
    private val DELAY_REFRESH = 100L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
    }

    fun initView(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = getString(R.string.my_apps)

        detailAdapter.listItem = requestType
        vp_detail?.apply {
            adapter = detailAdapter
            addOnPageChangeListener(this@DetailActivity)
        }
        pager_indicator?.count = detailAdapter.count
        pager_indicator?.setSelected(detailAdapter.getPosition(vp_detail.currentItem))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
