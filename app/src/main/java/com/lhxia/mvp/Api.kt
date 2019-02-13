package com.lhxia.mvp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.util.concurrent.Executors

/**
 * @Author : xialonghua
 * @Date : Create in 2019/1/22
 * @Description : a new file
 */
interface Api {


    @GET("/")
    fun getBaidu() : Call<String>
}

val retrofit = Retrofit.Builder()
    .callbackExecutor(Executors.newSingleThreadExecutor())
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl("https://www.baidu.com/") //设置网络请求的Url地址
    .build()
val api = retrofit.create(Api::class.java)
