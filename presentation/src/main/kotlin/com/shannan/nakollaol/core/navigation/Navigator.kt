
package com.shannan.nakollaol.core.navigation

import android.content.Context
import com.shannan.nakollaol.features.authentication.AuthenticationActivity
import com.shannan.nakollaol.features.order.OrderActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor() {

    fun showAuthScreen(context: Context) = context.startActivity(AuthenticationActivity.callingIntent(context))

    fun showOrderScreen(context: Context) = context.startActivity(OrderActivity.callingIntent(context))
}


