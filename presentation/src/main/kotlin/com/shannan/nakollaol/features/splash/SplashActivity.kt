package com.shannan.nakollaol.features.splash

import android.os.Bundle
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.platform.BaseActivity
import com.shannan.nakollaol.core.platform.BaseFragment


class SplashActivity : BaseActivity() {

    override fun getToolbarTitleResource() = 0

    override fun layoutId(): Int = R.layout.activity_layout

    override fun getFragment(bundle: Bundle): BaseFragment = SplashFragment.newInstance()

    override fun afterInflation(savedInstanceState: Bundle?) {
        addFragment(savedInstanceState)
    }
}