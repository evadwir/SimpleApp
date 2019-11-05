package com.simpleapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.simpleapp.R
import kotlinx.android.synthetic.main.item_adapter.view.*

class ItemAdapter(val listener : (Int) -> Unit) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var listItem = mutableListOf<Int>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false))
    }

    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView?.img?.setImageResource(listItem[position])
        holder.itemView?.setOnClickListener {
            listener.invoke(listItem[position])
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}