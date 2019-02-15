package com.lhxia.mvp.main.fragment.piclist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.lhxia.mvp.R
import com.lhxia.mvp.glide.GlideApp
import com.lhxia.mvp.main.fragment.piclist.bean.Pic
import kotlinx.android.synthetic.main.item_recyclerview_piclist.view.*

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/14
 * @Description : a new file
 */
class PicListAdapter : RecyclerView.Adapter<PicListAdapter.VH>() {

    val picList = arrayListOf<Pic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_piclist, parent, false))
    }

    override fun getItemCount(): Int {
        return picList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val pic = picList[position]
        holder.itemView.apply {


            title.text = pic.title
            imgCount.text = context.getString(R.string.pic_count, pic.count)
            GlideApp.with(img).load(pic.url).centerCrop().into(img)
            GlideApp.with(authHead).load(pic.url).into(authHead)
        }

    }


    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}