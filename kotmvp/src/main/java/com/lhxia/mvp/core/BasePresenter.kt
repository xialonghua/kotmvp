package com.lhxia.mvp.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : a new file
 */
abstract class BasePresenter(open val view : Contract.View<*>,
                             private val parentContext: CoroutineContext = EmptyCoroutineContext) : Contract.Presenter, CoroutineScope {
    var job : Job? = null

    override val coroutineContext: CoroutineContext
        get() {
            return if (parentContext is EmptyCoroutineContext){
                if (job == null){
                    job = SupervisorJob()
                }
                Dispatchers.Main + job!!
            }else {
                parentContext
            }
        }

    override fun onCreate(){}

    override fun onDestroy() {
        job?.cancel()
    }
}