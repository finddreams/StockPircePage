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
import com.finddreams.stockpircepage.vlayout.adapter.BottomFundAnalysisAdapter
import com.finddreams.stockpircepage.vlayout.adapter.BottomListAdapter
import com.finddreams.stockpircepage.vlayout.adapter.TopPriceInfoAdapter
import com.finddreams.stockpircepage.vlayout.adapter.TradeAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.LinkedList

/**
 * 报价页面的Vlayout实现
 */
class StockPriceVLayoutActivity : ComponentActivity() {
    private val TAG: String = "TAG"
    private lateinit var mBinding: ActivityStockpriceLayoutBinding
    private var mAdapters = LinkedList<DelegateAdapter.Adapter<*>>()
    private var bottomListAdapter: BottomListAdapter? = null
    private var isIndex = false
    private var itemList: List<StockItem>? = null
    private var code: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stockprice_layout)
        mBinding = ActivityStockpriceLayoutBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        getIntentData()
        val vlayoutUtils = VLayoutUtils()
        val delegateAdapter: DelegateAdapter =
            vlayoutUtils.initRecyclerView(mBinding.recycleView, this)
        val topPriceInfoAdapter = TopPriceInfoAdapter(this, code)
        mAdapters.add(topPriceInfoAdapter)
        mAdapters.add(
            vlayoutUtils.initLineLayoutAdapter(
                this,
                R.layout.recycleview_item_center,
                ViewTypeChart
            )
        )
        //指数底部只有成分股股列表
        if (isIndex) {
            itemList = List(1200) { StockItem("腾讯控股 $it") }
            mAdapters.add(vlayoutUtils.initStickTopAdapter(this))
            bottomListAdapter = BottomListAdapter(this)
            mAdapters.add(bottomListAdapter!!)
            updateBottomList()
        } else {
            val tradeAdapter = TradeAdapter(this)
            mAdapters.add(tradeAdapter)
            mAdapters.add(
                vlayoutUtils.initLineLayoutAdapter(
                    this,
                    R.layout.recycleview_item_buysellpan,
                    ViewTypeBuSellPan
                )
            )
            val bottomFundAnalysisAdapter = BottomFundAnalysisAdapter(this)
            mAdapters.add(bottomFundAnalysisAdapter)
        }
        //设置适配器
        delegateAdapter.setAdapters(mAdapters)
        lifecycleScope.launch {
            while (true) {
                delay(5000L)
                val random = Math.random()
                itemList = List(1200) { StockItem("腾讯控股 $it $random") }
                updateBottomList()
            }
        }
    }

    private fun updateBottomList() {
        bottomListAdapter?.update(itemList)
    }

    private fun getIntentData() {
        intent.getStringExtra("code")?.let {
            code = it
            mBinding.topTitle.tvStockName.text = if (code == "000000") "恒生指数" else "腾讯控股"
            isIndex = it == "000000"
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