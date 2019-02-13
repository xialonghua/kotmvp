package com.lhxia.mvp

import com.lhxia.kotmvp.model.DefaultResultDecoder
import com.lhxia.kotmvp.model.RetrofitModelRequest
import com.lhxia.kotmvp.model.await
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