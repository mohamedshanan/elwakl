package com.shannan.nakollaol.features.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.shannan.nakollaol.R
import com.shannan.nakollaol.core.platform.BaseActivity
import com.shannan.nakollaol.features.restaurants.RestaurantModel

class AuthenticationActivity : BaseActivity() {

    override fun getToolbarTitleResource() = R.string.code_verification_screen_title

    override fun layoutId(): Int = R.layout.activity_layout

    override fun afterInflation(savedInstanceState: Bundle?) {
        addFragment(savedInstanceState)
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, AuthenticationActivity::class.java)
        fun callingIntent2(context: Context, restaurantModel: RestaurantModel) = Intent(context, AuthenticationActivity::class.java)

    }

    override fun fragment() = AuthenticationFragment.newInstance()
}
