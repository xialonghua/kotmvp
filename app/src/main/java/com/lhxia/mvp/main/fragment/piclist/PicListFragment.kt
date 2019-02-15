package com.lhxia.mvp.main.fragment.piclist

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lhxia.mvp.R
import com.lhxia.mvp.base.BaseFragment
import com.lhxia.mvp.main.fragment.piclist.bean.Pic
import kotlinx.android.synthetic.main.fragment_piclist.*
import org.jetbrains.anko.support.v4.dip

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/14
 * @Description : a new file
 */
class PicListFragment: BaseFragment<PicListContract.PicListPresenter>(), PicListContract.PicListView {

    lateinit var adapter : PicListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_piclist, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setPresenter(PicListPresenterImpl(this, coroutineContext))

        toolBar.title = getString(R.string.tab_pic)

        swipeRefreshLayout.setOnRefreshListener {
            getPresenter().loadPicList(-1)
        }
        adapter = PicListAdapter()
        picListRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        picListRecyclerView.adapter = adapter
        picListRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)

                outRect.bottom = dip(5)
            }
        })
    }

    override fun addPicList(picList: List<Pic>, clear: Boolean) {
        val lastPosition = if (clear){
            adapter.picList.clear()
            adapter.notifyDataSetChanged()
            0
        }else {
            adapter.picList.size - 1
        }
        adapter.picList.addAll(picList)
        adapter.notifyItemRangeInserted(lastPosition, picList.size)
    }

    override fun finishRefresh() {
        swipeRefreshLayout.isRefreshing = false
    }
}
