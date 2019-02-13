package com.lhxia.mvp.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : a new file
 */
open class BaseMPVFragment<P : Contract.Presenter> : Fragment(), CoroutineScope, Contract.View<P> {

    val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job


    private lateinit var presenter: P

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        getPresenter().onDestroy()
    }

    override fun setPresenter(presenter: P) {
        this.presenter = presenter
        getPresenter().onCreate()
    }

    override fun getPresenter(): P = presenter
}