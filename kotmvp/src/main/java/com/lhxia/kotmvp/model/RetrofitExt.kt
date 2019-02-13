package com.lhxia.kotmvp.model

import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * @Author : xialonghua
 * @Date : Create in 2019/1/22
 * @Description : a new file
 */
interface ResultDecoder<T>{

    @Throws(Exception::class)
    fun decode(response: Response<T>) : T
}

class DefaultResultDecoder : ResultDecoder<String> {

    @Throws(Exception::class)
    override fun decode(response: Response<String>) : String {
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw NetworkErrorException()
        }
    }
}

suspend fun <T> Call<T>.await(decoder: ResultDecoder<T>): T = suspendCancellableCoroutine { cont ->

    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            try {
                Thread.sleep(3000)
                cont.resume(decoder.decode(response))
            }catch (e : Exception){
                cont.resumeWithException(e)
            }
        }
        override fun onFailure(call: Call<T>, t: Throwable) {
            if (call.isCanceled){
                cont.resumeWithException(CancellationException())
            }else {
                cont.resumeWithException(t)
            }
        }
    })

    cont.invokeOnCancellation {
        try {
            this@await.cancel()
        }catch (e: Exception){}
    }

}