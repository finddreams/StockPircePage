package com.finddreams.stockpircepage.vlayout


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.vlayout.DelegateAdapter
import com.finddreams.stockpircepage.R
import com.finddreams.stockpircepage.databinding.ActivityStockpriceLayoutBinding
import com.finddreams.stockpircepage.model.StockItem
import com.finddreams.stockpircepage.vlayout.adapter.BottomFundFlowAdapter
import com.finddreams.stockpircepage.vlayout.adapter.BottomListAdapter
import com.finddreams.stockpircepage.vlayout.adapter.TopPriceInfoAdapter
import com.finddreams.stockpircepage.vlayout.adapter.TradeAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.LinkedList


class StockPriceVLayoutActivity : ComponentActivity() {
    private val TAG: String = "TAG"
    private lateinit var mBinding: ActivityStockpriceLayoutBinding
    private var mAdapters = LinkedList<DelegateAdapter.Adapter<*>>()
    private var bottomListAdapter: BottomListAdapter? = null
    private var isIndex = false
    private var itemList: List<StockItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stockprice_layout)
        mBinding = ActivityStockpriceLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        intent.getStringExtra("code")?.let {
            mBinding.topTitle.tvStockName.text = it
            isIndex = it=="恒生指数"
        }
        val vlayoutUtils = VLayoutUtils()

        val delegateAdapter: DelegateAdapter =
            vlayoutUtils.initRecyclerView(mBinding.recycleView, this)
        val topPriceInfoAdapter = TopPriceInfoAdapter(this)
        mAdapters.add(topPriceInfoAdapter)
        mAdapters.add(
            vlayoutUtils.initLineLayoutAdapter(
                this,
                R.layout.recycleview_item_center,
                ViewTypeChart
            )
        )
        val tradeAdapter = TradeAdapter(this)
        mAdapters.add(tradeAdapter)
        mAdapters.add(
            vlayoutUtils.initLineLayoutAdapter(
                this,
                R.layout.recycleview_item_buysellpan,
                ViewTypeBuSellPan
            )
        )
        if (isIndex) {
            itemList = List(1200) { StockItem("腾讯控股 $it") }
            mAdapters.add(vlayoutUtils.initStickTopAdapter(this))
            bottomListAdapter = BottomListAdapter(this)
            mAdapters.add(bottomListAdapter!!)
            bottomListAdapter?.update(itemList)
        } else {
            val bottomFundFlowAdapter = BottomFundFlowAdapter(this)
            mAdapters.add(bottomFundFlowAdapter)
        }
        //设置适配器
        delegateAdapter.setAdapters(mAdapters)
        lifecycleScope.launch {
            while (true) {
                delay(5000L)
                bottomListAdapter?.update(itemList)
            }
        }
    }

    companion object {
        fun startActivity(context: Context, code: String) {
            val intent = Intent(context, StockPriceVLayoutActivity::class.java)
            if (code.isNotEmpty()) {
                intent.putExtra("code", code)
            }
            context.startActivity(intent)
        }
    }
}