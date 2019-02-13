package com.lhxia.mvp

import com.lhxia.mvp.model.DefaultResultDecoder
import com.lhxia.mvp.model.RetrofitModelRequest
import com.lhxia.mvp.model.await
import retrofit2.Call

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : a new file
 */
class SrtingRetrofitModelRequest(private val call: Call<String>) : RetrofitModelRequest<String>(call) {
    override suspend fun getResp(): String {
        return call.await(DefaultResultDecoder())
    }
}