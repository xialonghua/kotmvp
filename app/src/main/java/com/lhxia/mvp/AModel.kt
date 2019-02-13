package com.lhxia.mvp

import com.lhxia.kotmvp.core.Model

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : a new file
 */
class AModel: Model {

    fun getInfo() : Model.ModelRequest<String>{
        val call = api.getBaidu()
        return SrtingRetrofitModelRequest(call)
    }
}