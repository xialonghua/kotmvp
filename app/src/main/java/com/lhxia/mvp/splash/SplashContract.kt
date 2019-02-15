package com.lhxia.mvp.splash

import com.lhxia.kotmvp.core.Contract
import com.lhxia.mvp.AContract

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/14
 * @Description : a new file
 */
interface SplashContract {

    interface SplashPresenter : Contract.Presenter {

        fun tick()
    }

    interface SplashView: Contract.View<SplashPresenter>{
        fun showIconAnim()

        fun jumpToMainActivity()
    }
}