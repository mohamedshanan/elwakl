package com.shannan.nakollaol.core.navigation

import android.content.Context
import com.shannan.nakollaol.features.authentication.AuthenticationActivity
import com.shannan.nakollaol.features.restaurantslist.RestaurantModel
import com.shannan.nakollaol.features.restaurantslist.RestaurantsActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor() {

    fun showAuthScreen(context: Context) = context.startActivity(AuthenticationActivity.callingIntent(context))
    fun showRestaurantScreen(context: Context, restaurantModel: RestaurantModel) =
            context.startActivity(AuthenticationActivity.callingIntent2(context, restaurantModel))

    fun showOrderScreen(context: Context) = context.startActivity(RestaurantsActivity.callingIntent(context))
}


