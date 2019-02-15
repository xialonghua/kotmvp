package com.lhxia.mvp

import com.lhxia.kotmvp.core.BasePresenter
import com.lhxia.kotmvp.model.NetworkErrorException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : a new file
 */
class APresenterImpl(override val view : AContract.AView, parentCoroutineContext: CoroutineContext = EmptyCoroutineContext)
    : BasePresenter(view, parentCoroutineContext), AContract.APresenter {

    private lateinit var aModel : AModel

    override fun onCreate() {
        super.onCreate()
//
//        launch {
//            delay(3000)
//            view.showError("11111")
//            val a = aModel.getInfo()
//            try {
//                view.setInfo(a.getResp())
//            } catch (e : NetworkErrorException){
//                view.showError("网络错误")
//            }
//            view.showError("网络错误22222")
//        }
    }
}