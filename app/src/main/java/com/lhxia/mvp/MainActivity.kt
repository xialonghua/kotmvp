package com.lhxia.mvp

import android.os.Bundle
import com.lhxia.kotmvp.core.BaseMPVActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

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
