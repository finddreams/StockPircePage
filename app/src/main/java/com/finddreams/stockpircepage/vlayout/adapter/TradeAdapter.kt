package com.finddreams.stockpircepage.vlayout.adapter

import android.content.Context
import android.util.Log
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.finddreams.stockpircepage.R
import com.finddreams.stockpircepage.model.StockItem
import com.finddreams.stockpircepage.vlayout.ViewTypeBottomFundFlow
import com.finddreams.stockpircepage.vlayout.ViewTypeTrade

class TradeAdapter(context: Context) : BaseDelegateAdapter(
    context,
    LinearLayoutHelper(),
    R.layout.recycleview_item_trade,
    1,
    ViewTypeTrade
) {
    val TAG = "BottomAdapter"
    private var itemList: List<StockItem>? = null

    override fun onBindViewHolder(holder: BaseDelegateViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)

    }

}