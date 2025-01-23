package com.finddreams.stockpircepage.vlayout.adapter

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.finddreams.stockpircepage.R
import com.finddreams.stockpircepage.model.StockItem
import com.finddreams.stockpircepage.vlayout.ViewTypeBottomList
import kotlin.time.Duration

class TopPriceInfoAdapter(val context: Context) : BaseDelegateAdapter(
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
        ll_price?.setOnClickListener {
            iv_arrow?.rotation = if (iv_arrow?.rotation == 0f) {
                180f
            } else {
                0f
            }
            //rl_expand 使用位移动画关闭和隐藏
            val targetHeight = dpToPx(100) // 展开的高度，100dp
            if (rl_expand?.visibility == LinearLayout.GONE) {
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
                val initialHeight = rl_expand?.height ?: 0
                val animator = ValueAnimator.ofInt(initialHeight, 0)
                animator.duration = DurationTime
                animator.addUpdateListener { animation ->
                    val value = animation.animatedValue as Int
                    val layoutParams = rl_expand?.layoutParams
                    layoutParams?.height = value
                    rl_expand?.layoutParams = layoutParams
                }
                animator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        rl_expand?.visibility = LinearLayout.GONE
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