# KotMVP
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/xialonghua/kotmvp.svg?branch=master)](https://travis-ci.org/xialonghua/kotmvp)
[![Download](https://api.bintray.com/packages/xialonghua/kotmvp/kotmvp/images/download.svg)](https://bintray.com/xialonghua/kotmvp/kotmvp/_latestVersion)

是一个Kotlin写的MVP脚手架。
一般的MVP框架都没有协程的支持。KotMVP将协程的特性引入到MVP中。并且抽象了Model层可以自定义自己的Model。使使用MVP变得更加简单。

# 包含功能
- MVP
- Coroutine
- Retrofit

# 如何集成
## Gradle
gradle加入：
```Gradle
    implementation 'com.lhxia.kotmvp:kotmvp:0.0.5'
```

# 如何使用
## 声明Contract
首先声明一个Contract。里面包含了Presenter以及View的子类
```Kotlin
interface AContract: Contract {

    interface APresenter: Contract.Presenter
    interface AView: Contract.View<APresenter>{
        fun setInfo(info : String)
        fun showError(error: String)
    }
}
```
## 实现Presenter
```Kotlin
class APresenterImpl(override val view : AContract.AView, parentCoroutineContext: CoroutineContext = EmptyCoroutineContext)
    : BasePresenter(view, parentCoroutineContext), AContract.APresenter {
}
```
## 继承BaseMPVActivity或BaseMPVFragment
```Kotlin
class MainActivity : BaseMPVActivity<AContract.APresenter>(), AContract.AView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPresenter(APresenterImpl(this, coroutineContext))
    }

    override fun setInfo(info : String) {
        haha.text = info
    }

    override fun showError(error: String) {
        toast("error : $error")
    }

}
```

## 使用Model
### 添加注解
首先实现Model接口，并且在自己的Model接口上添加@Repo(implClass = AModel::class)注解设置实现类。
```Kotlin
@Repo(implClass = AModel::class)
class AModel: Model {

    fun getInfo() : Model.ModelRequest<String>{
        val call = api.getBaidu()
        return SrtingRetrofitModelRequest(call)
    }
}
```
### 声明
直接在Presenter里声明即可
```Kotlin
private lateinit var aModel : AModel
```
### ModelRequest
抽象接口，自己实现。支持取消操作。注意getResp()为suspend方法支持协程
```Kotlin
interface ModelRequest<T> {

        @Throws(Exception::class)
        suspend fun getResp(): T

        fun cancel()
    }
```
如果是retrofit可以使用内置的实现RetrofitModelRequest<T>。
```Kotlin
abstract class RetrofitModelRequest<T>(private var call: Call<T>)
    : Model.ModelRequest<T> {

    override fun cancel() {
        call.cancel()
    }

}
```
比如返回值为String的接口。其中DefaultResultDecoder实现ResultDecoder接口为服务器返回解析出具体的类型
```Kotlin
class SrtingRetrofitModelRequest(private val call: Call<String>) : RetrofitModelRequest<String>(call) {
    override suspend fun getResp(): String {
        return call.await(DefaultResultDecoder())
    }
}
```
```Kotlin
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
```
## 使用协程
可以直接在presenter中使用协程
```Kotlin
class APresenterImpl(override val view : AContract.AView, parentCoroutineContext: CoroutineContext = EmptyCoroutineContext)
    : BasePresenter(view, parentCoroutineContext), AContract.APresenter {

    private lateinit var aModel : AModel

    override fun onCreate() {
        super.onCreate()

        launch {
            delay(3000)
            view.showError("11111")
            val a = aModel.getInfo()
            try {
                view.setInfo(a.getResp())
            } catch (e : NetworkErrorException){
                view.showError("网络错误")
            }
            view.showError("网络错误22222")
        }
    }
}
```
## Demo
详情可以参考module:app中的示例
