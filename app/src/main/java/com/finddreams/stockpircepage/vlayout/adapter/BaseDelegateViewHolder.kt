package com.finddreams.stockpircepage.vlayout.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.View
import android.view.View.OnLongClickListener
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BaseDelegateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    // SparseArray 比 HashMap 更省内存，在某些条件下性能更好，只能存储 key 为 int 类型的数据，
    // 用来存放 View 以减少 findViewById 的次数
    private var viewSparseArray: SparseArray<View?> = SparseArray<View?>()

    /**
     * 获取item的对象
     */
    //这个是item的对象
    private var itemContentView: View? =null

    init {
        itemContentView = itemView
        viewSparseArray = SparseArray()
    }

    /**
     * 根据 ID 来获取 View
     * @param viewId viewID
     * @param <T>    泛型
     * @return 将结果强转为 View 或 View 的子类型
    </T> */
    fun <T : View?> getView(viewId: Int): T? {
        // 先从缓存中找，找打的话则直接返回
        // 如果找不到则 findViewById ，再把结果存入缓存中
        var view = viewSparseArray[viewId]
        if (view == null) {
            view = itemView.findViewById(viewId)
            viewSparseArray.put(viewId, view)
        }
        return view as T?
    }

    /**
     * 设置TextView的值
     */
    fun setText(viewId: Int, text: String?): BaseDelegateViewHolder {
        val tv = getView<TextView>(viewId)
        tv?.text = text
        return this
    }

    /**
     * 设置imageView图片
     */
    fun setImageResource(viewId: Int, resId: Int): BaseDelegateViewHolder {
        val view = getView<ImageView>(viewId)!!
        view.setImageResource(resId)
        return this
    }

    /**
     * 设置imageView图片
     */
    fun setImageBitmap(viewId: Int, bitmap: Bitmap?): BaseDelegateViewHolder {
        val view = getView<ImageView>(viewId)!!
        view.setImageBitmap(bitmap)
        return this
    }

    /**
     * 设置imageView图片
     */
    fun setImageDrawable(viewId: Int, drawable: Drawable?): BaseDelegateViewHolder {
        val view = getView<ImageView>(viewId)
        view?.setImageDrawable(drawable)
        return this
    }

    /**
     * 设置背景颜色
     */
    fun setBackgroundColor(viewId: Int, color: Int): BaseDelegateViewHolder {
        val view = getView<View>(viewId)
        view?.setBackgroundColor(color)
        return this
    }

    /**
     * 设置背景颜色
     */
    fun setBackgroundRes(viewId: Int, backgroundRes: Int): BaseDelegateViewHolder {
        val view = getView<View>(viewId)
        view?.setBackgroundResource(backgroundRes)
        return this
    }

    /**
     * 设置text颜色
     */
    fun setTextColor(viewId: Int, textColor: Int): BaseDelegateViewHolder {
        val view = getView<TextView>(viewId)
        view?.setTextColor(textColor)
        return this
    }

    /**
     * 设置透明度
     */
    @SuppressLint("NewApi")
    fun setAlpha(viewId: Int, value: Float): BaseDelegateViewHolder {
        getView<View>(viewId)!!.setAlpha(value)
        return this
    }

    /**
     * 设置是否可见
     */
    fun setVisible(viewId: Int, visible: Boolean): BaseDelegateViewHolder {
        val view = getView<View>(viewId)
        view?.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    fun setTypeface(typeface: Typeface?, vararg viewIds: Int): BaseDelegateViewHolder {
        for (viewId in viewIds) {
            val view = getView<TextView>(viewId)!!
            view.setTypeface(typeface)
            view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        }
        return this
    }

    fun setTag(viewId: Int, tag: Any?): BaseDelegateViewHolder {
        val view = getView<View>(viewId)!!
        view.tag = tag
        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any?): BaseDelegateViewHolder {
        val view = getView<View>(viewId)!!
        view.setTag(key, tag)
        return this
    }

    /**
     * 关于事件的
     */
    fun setOnClickListener(viewId: Int, listener: View.OnClickListener?): BaseDelegateViewHolder {
        val view = getView<View>(viewId)
        view?.setOnClickListener(listener)
        return this
    }

    fun setOnTouchListener(viewId: Int, listener: OnTouchListener?): BaseDelegateViewHolder {
        val view = getView<View>(viewId)
        view?.setOnTouchListener(listener)
        return this
    }

    fun setOnLongClickListener(
        viewId: Int,
        listener: OnLongClickListener?
    ): BaseDelegateViewHolder {
        val view = getView<View>(viewId)
        view?.setOnLongClickListener(listener)
        return this
    }
}
