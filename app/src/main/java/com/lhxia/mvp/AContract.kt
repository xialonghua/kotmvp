package com.lhxia.mvp

import com.lhxia.kotmvp.core.Contract

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : a new file
 */
interface AContract: Contract {

    interface APresenter: Contract.Presenter
    interface AView: Contract.View<APresenter>{
        fun showError(error: String)
    }
}