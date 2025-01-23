package com.finddreams.stockpircepage.vlayout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper

open class BaseDelegateAdapter protected constructor(
    private val mContext: Context,
    layoutHelper: LayoutHelper,
    layoutId: Int,
    count: Int,
    viewTypeItem: Int
) : DelegateAdapter.Adapter<BaseDelegateViewHolder>() {
    private val mLayoutHelper: LayoutHelper
    protected var mCount = -1
    private var mLayoutId = -1
    private var mViewTypeItem = -1

    init {
        this.mCount = count
        this.mLayoutHelper = layoutHelper
        this.mLayoutId = layoutId
        this.mViewTypeItem = viewTypeItem
    }

    override fun onCreateLayoutHelper(): LayoutHelper {
        return mLayoutHelper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseDelegateViewHolder {
        val rootView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false)
        if (viewType == mViewTypeItem) {
            return BaseDelegateViewHolder(
                rootView
            )
        }
        return BaseDelegateViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: BaseDelegateViewHolder, position: Int) {
    }

    /**
     * 必须重写不然会出现滑动不流畅的情况
     */
    override fun getItemViewType(position: Int): Int {
        return mViewTypeItem
    }

    /**
     * 条目数量
     * @return          条目数量
     */
    override fun getItemCount(): Int {
        return mCount
    }
}
