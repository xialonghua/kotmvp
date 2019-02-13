# KotMVP
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/xialonghua/kotmvp.svg?branch=master)](https://travis-ci.org/xialonghua/kotmvp)
[![Download](https://api.bintray.com/packages/xialonghua/kotmvp/kotmvp/images/download.svg)](https://bintray.com/xialonghua/kotmvp/kotmvp/_latestVersion)

是一个Kotlin写的MVP脚手架。

# Coroutine（协程）
一般的MVP框架都没有协程的支持。KotMVP将协程的特性引入到MVP中。并且抽象了Model层可以自定义自己的Model。

# 包含功能
- MVP
- Coroutine
- Retrofit

# 如何集成
## Gradle
gradle加入：
```Gradle
    implementation 'com.lhxia.kotmvp:kotmvp:0.0.4'
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
## 使用协程
