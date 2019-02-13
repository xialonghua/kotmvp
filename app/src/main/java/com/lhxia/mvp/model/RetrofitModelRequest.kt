package com.lhxia.mvp.model

import com.lhxia.mvp.core.Model
import retrofit2.Call

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : a new file
 */
abstract class RetrofitModelRequest<T>(private var call: Call<T>)
    : Model.ModelRequest<T> {

    override fun cancel() {
        call.cancel()
    }

}