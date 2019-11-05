package com.simpleapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.simpleapp.R
import com.simpleapp.activity.DetailActivity
import com.simpleapp.adapter.ItemAdapter
import kotlinx.android.synthetic.main.layout_home.*

class HomeFragment : Fragment() {

    private val itemListener :  (Int) -> Unit = {

        val itemDetail = arrayListOf<Int>()
        itemDetail.add(it)
        itemDetail.add(R.drawable.kucing11)
        itemDetail.add(R.drawable.kucing2)
        itemDetail.add(R.drawable.kucing3)

        val intent = Intent(activity, DetailActivity::class.java)
        intent.putIntegerArrayListExtra("data", itemDetail)
        activity?.startActivityForResult(intent, 11)

    }

    private val itemAdapter = ItemAdapter(itemListener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    fun initView(){
        itemAdapter.listItem.clear()
        itemAdapter.listItem.add(R.drawable.kucing1)
        itemAdapter.listItem.add(R.drawable.kucing2)
        itemAdapter.listItem.add(R.drawable.kucing3)
        rv_item?.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
    }
}