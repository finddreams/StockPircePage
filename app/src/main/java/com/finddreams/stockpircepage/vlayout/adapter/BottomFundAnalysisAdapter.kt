package com.finddreams.stockpircepage.vlayout.adapter

import android.content.Context
import android.util.Log
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.finddreams.stockpircepage.R
import com.finddreams.stockpircepage.model.StockItem
import com.finddreams.stockpircepage.vlayout.ViewTypeBottomFundFlow

class BottomFundAnalysisAdapter(context: Context) : BaseDelegateAdapter(
    context,
    LinearLayoutHelper(),
    R.layout.recycleview_item_fundanalysis,
    1,
    ViewTypeBottomFundFlow
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

}