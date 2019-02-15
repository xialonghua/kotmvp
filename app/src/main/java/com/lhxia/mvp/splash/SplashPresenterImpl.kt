package com.lhxia.mvp.splash

import com.lhxia.kotmvp.core.BasePresenter
import com.lhxia.mvp.AContract
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/14
 * @Description : a new file
 */

class SplashPresenterImpl(override val view : SplashContract.SplashView, parentCoroutineContext: CoroutineContext = EmptyCoroutineContext)
    : BasePresenter(view, parentCoroutineContext), SplashContract.SplashPresenter {
    override fun tick() {

        view.showIconAnim()
        launch {
            repeat(1){
                delay(1000)
            }
            view.jumpToMainActivity()
        }

    }
}