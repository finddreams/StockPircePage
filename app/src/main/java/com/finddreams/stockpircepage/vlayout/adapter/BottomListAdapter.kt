package com.finddreams.stockpircepage.vlayout.adapter

import android.content.Context
import android.util.Log
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.finddreams.stockpircepage.R
import com.finddreams.stockpircepage.model.StockItem
import com.finddreams.stockpircepage.vlayout.ViewTypeBottomList

class BottomListAdapter(context: Context) : BaseDelegateAdapter(
    context,
    LinearLayoutHelper(),
    R.layout.recycleview_item_bottom,
    0,
    ViewTypeBottomList
) {
    val TAG = "BottomAdapter"
    private var itemList: List<StockItem>? = null

    override fun onBindViewHolder(holder: BaseDelegateViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        itemList?.get(position)?.apply {
            Log.i(TAG, "onBindViewHolder: . $name $position")
            holder.setText(R.id.tv_stock_name, name)
        }
    }

    fun update(itemList: List<StockItem>?) {
        if (itemList != null) {
            this.itemList = itemList
            mCount = itemList.size
//            notifyDataSetChanged()//会更新其他的Adapter
            notifyItemRangeChanged(0,mCount)
        }
        Log.i(TAG, "update: ...")
    }
}