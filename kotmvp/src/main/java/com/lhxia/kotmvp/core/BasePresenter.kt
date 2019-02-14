package com.lhxia.kotmvp.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.system.measureTimeMillis

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

    override fun onCreate(){
        javaClass.declaredFields.forEach {
            val an = it.type.getAnnotation(Repo::class.java) ?: return@forEach
            var instance =  modelInstanceCache[an.implClass.java.canonicalName]
            if(instance == null){
                instance = an.implClass.java.newInstance() as Model
                modelInstanceCache[an.implClass.java.canonicalName] = instance
            }
            it.isAccessible = true
            it.set(this, instance)
        }
    }

    override fun onDestroy() {
        job?.cancel()
    }
}

val modelInstanceCache = HashMap<String?, Model>()