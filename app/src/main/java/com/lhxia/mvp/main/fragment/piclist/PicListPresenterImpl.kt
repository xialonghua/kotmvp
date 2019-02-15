package com.lhxia.mvp.main.fragment.piclist

import com.lhxia.kotmvp.core.BasePresenter
import com.lhxia.mvp.main.fragment.piclist.bean.Pic
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/14
 * @Description : a new file
 */
class PicListPresenterImpl(override val view : PicListContract.PicListView, parentCoroutineContext: CoroutineContext = EmptyCoroutineContext)
    : BasePresenter(view, parentCoroutineContext), PicListContract.PicListPresenter {

    override fun loadPicList(index: Int) {
        if (index == -1){
            view.addPicList(makeData(), true)
        }else {
            view.addPicList(makeData(), false)
        }
        view.finishRefresh()
    }


    private fun makeData() : List<Pic>{
        return arrayListOf<Pic>().apply {
            repeat(10){
                add(Pic("美女在干嘛呢？",
                    "http://k.zol-img.com.cn/dcbbs/12561/a12560163_s.jpg",
                    System.currentTimeMillis(), 10))
                add(Pic("美女在看哪呢",
                    "http://k.zol-img.com.cn/dcbbs/20888/a20887758_01000.jpg",
                    System.currentTimeMillis(), 5))
                add(Pic("美女骑着摩托",
                    "http://img8.zol.com.cn/bbs/upload/20089/20088756_0800.JPG",
                    System.currentTimeMillis(), 10))
                add(Pic("清纯时尚美女",
                    "http://wx1.sinaimg.cn/large/005ECCmtly1fk6bbp609sj30uk0l9tbq.jpg",
                    System.currentTimeMillis(), 20))


            }
        }
    }
}