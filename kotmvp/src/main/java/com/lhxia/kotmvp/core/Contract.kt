package com.lhxia.kotmvp.core

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : 契约类
 */
interface Contract {
    interface Presenter {
        fun onCreate()
        fun onDestroy()
    }
    interface View<P : Presenter>{

        fun getPresenter(): P
        fun setPresenter(presenter: P)
    }
}