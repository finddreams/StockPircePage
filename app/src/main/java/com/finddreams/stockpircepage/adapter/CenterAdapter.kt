package com.finddreams.stockpircepage.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.finddreams.stockpircepage.R

class CenterAdapter : RecyclerView.Adapter<CenterAdapter.ContentViewHolder>() {
    private val TAG = "CenterAdapter"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item_center, parent, false)
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder: $position")
    }

    override fun getItemCount(): Int = 1

    class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }
}
