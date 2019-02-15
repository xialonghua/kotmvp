package com.lhxia.mvp.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import com.lhxia.kotmvp.core.BaseMPVActivity
import com.lhxia.mvp.AContract
import com.lhxia.mvp.APresenterImpl
import com.lhxia.mvp.R
import com.lhxia.mvp.base.BaseFragment
import com.lhxia.mvp.main.fragment.piclist.PicListFragment
import com.lhxia.mvp.view.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.tableLayout
import org.jetbrains.anko.toast

class MainActivity : BaseMPVActivity<AContract.APresenter>(), AContract.AView {

    private val fragments = arrayOfNulls<BaseFragment<*>>(3)
    private var lastIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPresenter(APresenterImpl(this, coroutineContext))

        initTabs()
    }


    private fun initTabs(){
        fragments[0] = PicListFragment()
        fragments[1] = PicListFragment()
        fragments[2] = PicListFragment()

        TabLayout.Tab().apply {
            text = getString(R.string.tab_pic)
            iconDrawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.tab_pic_selector)!!
            tabLayout.addTab(this)

        }

        TabLayout.Tab().apply {
            text = getString(R.string.tab_video)
            iconDrawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.tab_pic_selector)!!
            tabLayout.addTab(this)
        }

        TabLayout.Tab().apply {
            text = getString(R.string.tab_my)
            iconDrawable = ContextCompat.getDrawable(this@MainActivity, R.drawable.tab_pic_selector)!!
            tabLayout.addTab(this)
        }

        tabLayout.layoutTab()

        tabLayout.onTabSelectListener = {index, _->
            toast("select $index")
            changeFragment(index)
        }
        tabLayout.selectTab(0)
    }

    override fun showError(error: String) {
        toast("error : $error")
    }


    fun changeFragment(index: Int){
        val transaction =  supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag("$index")
        if (fragment == null){
            fragment = fragments[index] as Fragment
            transaction.add(R.id.content, fragment, "$index")
        }
        if (lastIndex > -1){
            val lastFragment = supportFragmentManager.findFragmentByTag("$lastIndex")
            transaction.hide(lastFragment!!)
        }
        transaction.show(fragment)
        transaction.commit()
        lastIndex = index
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {}
}
