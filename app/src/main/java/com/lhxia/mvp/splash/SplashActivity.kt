package com.lhxia.mvp.splash

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.lhxia.kotmvp.core.BaseMPVActivity
import com.lhxia.mvp.main.MainActivity
import com.lhxia.mvp.R
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity

class SplashActivity : BaseMPVActivity<SplashContract.SplashPresenter>(), SplashContract.SplashView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setPresenter(SplashPresenterImpl(this, coroutineContext))

        getPresenter().tick()
    }

    override fun showIconAnim() {

        launch {
            delay(200)
            layout.visibility = View.VISIBLE

            layout.startAnimation(AnimationUtils.loadAnimation(this@SplashActivity, android.R.anim.fade_in))
        }
    }

    override fun jumpToMainActivity() {
        startActivity<MainActivity>()
        finish()
    }

    override fun onBackPressed() {
        //do nothing
    }

}
