package com.shannan.nakollaol.features.binverification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.platform.BaseActivity

class BinVerificationActivity : BaseActivity() {

    override fun getToolbarTitleResource() = R.string.bin_screen_title

    override fun afterInflation(savedInstanceState: Bundle?) {
        addFragment(savedInstanceState)
    }

    override fun layoutId(): Int = R.layout.activity_layout

    companion object {
        fun callingIntent(context: Context) = Intent(context, BinVerificationActivity::class.java)
    }

    override fun fragment() = BinVerificationFragment.newInstance()
}