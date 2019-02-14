package com.lhxia.mvp

import com.lhxia.kotmvp.core.Model
import com.lhxia.kotmvp.core.Repo

/**
 * @Author : xialonghua
 * @Date : Create in 2019/2/13
 * @Description : a new file
 */
@Repo(implClass = AModel::class)
class AModel: Model {

    fun getInfo() : Model.ModelRequest<String>{
        val call = api.getBaidu()
        return SrtingRetrofitModelRequest(call)
    }
}