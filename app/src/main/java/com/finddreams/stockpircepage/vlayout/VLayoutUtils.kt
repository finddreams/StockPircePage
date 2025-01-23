package com.finddreams.stockpircepage.vlayout

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.VirtualLayoutManager
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.alibaba.android.vlayout.layout.StickyLayoutHelper
import com.finddreams.stockpircepage.R
import com.finddreams.stockpircepage.vlayout.adapter.BaseDelegateAdapter
import com.finddreams.stockpircepage.vlayout.adapter.BaseDelegateViewHolder

const val ViewTypePrice: Int = 1001
const val ViewTypeChart: Int = 1002
const val ViewTypeBuSellPan: Int = 1003
const val ViewTypeStickTop: Int = 1004
const val ViewTypeTrade: Int = 1005
const val ViewTypeBottomList: Int = 1006
const val ViewTypeBottomFundFlow: Int = 1007

val TAG = "VLayoutUtils"

class VLayoutUtils {


    fun initRecyclerView(recyclerView: RecyclerView, context: Context?): DelegateAdapter {
        //初始化
        //创建VirtualLayoutManager对象
        val layoutManager = VirtualLayoutManager(context!!)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.itemAnimator = null
        //设置回收复用池大小，（如果一屏内相同类型的 View 个数比较多，需要设置一个合适的大小，防止来回滚动时重新创建 View）
        val viewPool = RecyclerView.RecycledViewPool()
        viewPool.setMaxRecycledViews(ViewTypePrice, 1)
        viewPool.setMaxRecycledViews(ViewTypeChart, 1)
        viewPool.setMaxRecycledViews(ViewTypeBuSellPan, 1)
        viewPool.setMaxRecycledViews(ViewTypeStickTop, 1)
        viewPool.setMaxRecycledViews(ViewTypeBottomList, 1000)
        viewPool.setMaxRecycledViews(ViewTypeBottomFundFlow, 1)
        recyclerView.setRecycledViewPool(viewPool)
        //设置适配器
        val delegateAdapter = DelegateAdapter(layoutManager)

        recyclerView.setAdapter(delegateAdapter)
        return delegateAdapter
    }

    fun initLineLayoutAdapter(
        context: Context,
        layoutId: Int,
        viewTypeItem: Int
    ): BaseDelegateAdapter {
        val layoutHelper = LinearLayoutHelper()
        return object :
            BaseDelegateAdapter(context, layoutHelper, layoutId, 1, viewTypeItem) {
            override fun onBindViewHolder(
                holder: BaseDelegateViewHolder,
                position: Int
            ) {
                super.onBindViewHolder(holder, position)
                Log.i(TAG, "initLineLayoutAdapter onBindViewHolder: $position")
            }
        }
    }

    fun initStickTopAdapter(context: Context): BaseDelegateAdapter {
        val layoutHelper = StickyLayoutHelper()
        return object :
            BaseDelegateAdapter(
                context,
                layoutHelper,
                R.layout.recycleview_item_sticktop,
                1,
                ViewTypeStickTop
            ) {
            override fun onBindViewHolder(
                holder: BaseDelegateViewHolder,
                position: Int
            ) {
                super.onBindViewHolder(holder, position)
            }
        }
    }

}
