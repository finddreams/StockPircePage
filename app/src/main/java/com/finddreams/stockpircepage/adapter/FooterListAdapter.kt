package com.finddreams.stockpircepage.adapter

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.finddreams.stockpircepage.model.StockItem

class FooterListAdapter(private val items: List<StockItem>) : RecyclerView.Adapter<FooterListAdapter.ContentViewHolder>() {
    val TAG = "FooterListAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item_1, parent, false)
        Log.i(TAG, "onCreateViewHolder: ")
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        Log.i(TAG,"onBindViewHolder $position")
        holder.textView.text = items[position].name
    }

    override fun getItemCount(): Int = items.size
    override fun getItemViewType(position: Int): Int {
        return 100
    }
    class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.text1)
    }
}
