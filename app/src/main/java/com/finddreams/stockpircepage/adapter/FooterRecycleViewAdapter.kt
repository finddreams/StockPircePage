package com.finddreams.stockpircepage.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finddreams.stockpircepage.R
import com.finddreams.stockpircepage.model.StockItem

class FooterRecycleViewAdapter(val context: Context,private val items: List<StockItem>) : RecyclerView.Adapter<FooterRecycleViewAdapter.ContentViewHolder>() {

    private var footerListAdapter: FooterListAdapter? = null
    val TAG = "FooterRecycleViewAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycleview_item_footer_recycleview, parent, false)
        Log.i(TAG, "onCreateViewHolder: ")
        return ContentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder $position")
        footerListAdapter = FooterListAdapter(items)
        holder.recyclerView.layoutManager = LinearLayoutManager(context)
        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        val viewPool = RecyclerView.RecycledViewPool()
        viewPool.setMaxRecycledViews(100, 2000)
        holder.recyclerView.setRecycledViewPool(viewPool)
        holder.recyclerView.adapter = footerListAdapter
        holder.recyclerView.setHasFixedSize(false) // 重要！
    }

    override fun getItemCount(): Int = 1

    class ContentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    }
    fun updateChildData(items: List<StockItem>) {
        footerListAdapter?.notifyDataSetChanged()
    }
}


