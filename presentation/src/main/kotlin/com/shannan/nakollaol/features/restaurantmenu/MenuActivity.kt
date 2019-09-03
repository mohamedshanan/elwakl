package com.shannan.nakollaol.features.restaurantmenu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.shannan.nakollaol.R
import com.shannan.nakollaol.Utils.Constants
import com.shannan.nakollaol.core.platform.BaseActivity
import com.shannan.nakollaol.core.platform.BaseFragment
import com.shannan.nakollaol.models.RestaurantModel

class MenuActivity : BaseActivity() {

    override fun getToolbarTitleResource() = 0

    override fun layoutId(): Int = R.layout.activity_layout

    override fun afterInflation(savedInstanceState: Bundle?) {
        addFragment(savedInstanceState)
    }

    companion object {
        fun callingIntent(context: Context, restaurantModel: RestaurantModel): Intent {
            val intent = Intent(context, MenuActivity::class.java)
            intent.extras.putParcelable(Constants.EXTRA_SANDWICHES, restaurantModel)
            return intent
        }
    }

    override fun getFragment(bundle: Bundle): BaseFragment {
        val menuFragment = MenuFragment.newInstance()
        menuFragment.arguments = bundle
        return menuFragment
    }
}