package com.finddreams.stockpircepage.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finddreams.stockpircepage.R

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private val TAG = "HeaderAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item_price, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder: $position")
    }

    override fun getItemCount(): Int = 1

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}
