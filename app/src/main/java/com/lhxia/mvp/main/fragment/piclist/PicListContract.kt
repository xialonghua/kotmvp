package com.lhxia.mvp.main.fragment.piclist

import com.lhxia.kotmvp.core.Contract
import com.lhxia.mvp.main.fragment.piclist.bean.Pic

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/14
 * @Description : a new file
 */
class PicListContract {

    interface PicListPresenter: Contract.Presenter {
        fun loadPicList(index: Int)
    }
    interface PicListView: Contract.View<PicListPresenter>{
        fun addPicList(picList: List<Pic>, clear: Boolean = false)

        fun finishRefresh()
    }
}