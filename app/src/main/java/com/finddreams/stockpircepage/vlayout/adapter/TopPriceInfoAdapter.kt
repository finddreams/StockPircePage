package com.finddreams.stockpircepage.vlayout.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.finddreams.stockpircepage.R
import com.finddreams.stockpircepage.vlayout.ViewTypeBottomList
import androidx.core.view.isGone

class TopPriceInfoAdapter(val context: Context, val code: String) : BaseDelegateAdapter(
    context,
    LinearLayoutHelper(),
    R.layout.recycleview_item_price,
    1,
    ViewTypeBottomList
) {
    private val DurationTime: Long = 250L
    val TAG = "TopPriceInfoAdapter"

    override fun onBindViewHolder(holder: BaseDelegateViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val ll_price = holder.getView<LinearLayout>(R.id.ll_price)
        val rl_expand = holder.getView<RelativeLayout>(R.id.rl_expand)
        val iv_arrow = holder.getView<ImageView>(R.id.iv_arrow)
        val tv_xj = holder.getView<TextView>(R.id.tv_xj)
        val tv_zdl = holder.getView<TextView>(R.id.tv_zdl)
        val tv_zdf = holder.getView<TextView>(R.id.tv_zdf)
        if (ll_price != null && iv_arrow != null && rl_expand != null) {
            setListeners(ll_price, iv_arrow, rl_expand)
        }
        Log.i(TAG, "onBindViewHolder: position:$position")
        if (code == "000000") {
            tv_xj?.text = "19700.56"
            tv_zdl?.text = "-78.21"
            tv_zdf?.text = "-0.40%"
            tv_xj?.setTextColor(context.resources.getColor(R.color.jyb_base_color_green))
            tv_zdl?.setTextColor(context.resources.getColor(R.color.jyb_base_color_green))
            tv_zdf?.setTextColor(context.resources.getColor(R.color.jyb_base_color_green))
        }
    }

    private fun setListeners(
        ll_price: LinearLayout,
        iv_arrow: ImageView,
        rl_expand: RelativeLayout
    ) {
        ll_price.setOnClickListener {
            iv_arrow.rotation = if (iv_arrow.rotation == 0f) {
                180f
            } else {
                0f
            }
            //rl_expand 使用位移动画关闭和隐藏
            val targetHeight = dpToPx(100) // 展开的高度，100dp
            if (rl_expand.isGone) {
                // 展开动画
                rl_expand.visibility = LinearLayout.VISIBLE
                val initialHeight = 0
                val animator = ValueAnimator.ofInt(initialHeight, targetHeight)
                animator.duration = DurationTime
                animator.addUpdateListener { animation ->
                    val value = animation.animatedValue as Int
                    val layoutParams = rl_expand.layoutParams
                    layoutParams.height = value
                    rl_expand.layoutParams = layoutParams
                }
                animator.start()
            } else {
                // 收起动画
                val initialHeight = rl_expand.height
                val animator = ValueAnimator.ofInt(initialHeight, 0)
                animator.duration = DurationTime
                animator.addUpdateListener { animation ->
                    val value = animation.animatedValue as Int
                    val layoutParams = rl_expand.layoutParams
                    layoutParams?.height = value
                    rl_expand.layoutParams = layoutParams
                }
                animator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        rl_expand.visibility = LinearLayout.GONE
                    }
                })
                animator.start()
            }
        }
    }

    // dp 转 px 的辅助方法
    private fun dpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }
}