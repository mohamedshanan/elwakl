package com.shannan.nakollaol.features.generateotp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.platform.BaseActivity

class OTPActivity : BaseActivity() {

    override fun getToolbarTitleResource() = R.string.bin_screen_code

    override fun layoutId(): Int = R.layout.activity_layout

    override fun afterInflation(savedInstanceState: Bundle?) {
        addFragment(savedInstanceState)
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, OTPActivity::class.java)
    }

    override fun fragment() = OTPFragment.newInstance()
}
