package com.lhxia.mvp.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.lhxia.mvp.R
import kotlinx.android.synthetic.main.tab_item.view.*

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/14
 * @Description : a new file
 */
class TabLayout(context: Context, attrs : AttributeSet?) : LinearLayout(context, attrs) {

    var onTabSelectListener : ((index: Int, tab: Tab)->Unit)? = null

    private val tabs = arrayListOf<Tab>()
    var currentIndex = -1
    init {
        orientation = HORIZONTAL
    }

    fun addTab(tab: Tab){
        tabs.add(tab)
    }

    fun layoutTab(){
        tabs.forEachIndexed { index, tab ->
            tab.index = index
            tab.view = LayoutInflater.from(context).inflate(R.layout.tab_item, null)
            tab.view.icon.setImageDrawable(tab.iconDrawable)
            tab.view.text.text = tab.text
            tab.view.tag = tab
            tab.view.setOnClickListener {

                val t = it.tag as Tab
                selectTab(t.index)

            }
            addView(tab.view, LinearLayout.LayoutParams(-1, -1).apply {
                weight = 1f
            })
        }
    }

    fun selectTab(selectIndex: Int){
        if(currentIndex == selectIndex){
            return
        }
        currentIndex = selectIndex
        onTabSelectListener?.invoke(selectIndex, tabs[selectIndex])
        tabs.forEachIndexed { index, tab ->
            if (index == selectIndex){
                tab.select()
            }else {
                tab.unSelect()
            }
        }
    }
    class Tab {

        var index = 0
        lateinit var view : View
        lateinit var text : CharSequence
        lateinit var iconDrawable : Drawable

        fun select(){
            view.icon.isSelected = true
        }

        fun unSelect(){
            view.icon.isSelected = false
        }
    }
}

