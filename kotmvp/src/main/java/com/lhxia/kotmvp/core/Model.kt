package com.lhxia.kotmvp.core

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : a new file
 */
interface Model {

    interface ModelRequest<T> {

        @Throws(Exception::class)
        suspend fun getResp(): T

        fun cancel()
    }
}