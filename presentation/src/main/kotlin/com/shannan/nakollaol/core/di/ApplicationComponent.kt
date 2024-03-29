
package com.shannan.nakollaol.core.di

import com.shannan.nakollaol.AndroidApplication
import com.shannan.nakollaol.core.di.viewmodel.ViewModelModule
import com.shannan.nakollaol.features.authentication.AuthenticationActivity
import com.shannan.nakollaol.features.authentication.AuthenticationFragment
import com.shannan.nakollaol.features.restaurantmenu.MenuActivity
import com.shannan.nakollaol.features.restaurantmenu.MenuFragment
import com.shannan.nakollaol.features.restaurantslist.RestaurantsActivity
import com.shannan.nakollaol.features.restaurantslist.RestaurantsFragment
import com.shannan.nakollaol.features.splash.SplashActivity
import com.shannan.nakollaol.features.splash.SplashFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(splashActivity: SplashActivity)
    fun inject(authenticationActivity: AuthenticationActivity)
    fun inject(restaurantsActivity: RestaurantsActivity)
    fun inject(menuActivity: MenuActivity)

    fun inject(splashFragment: SplashFragment)
    fun inject(authenticationFragment: AuthenticationFragment)
    fun inject(restaurantsFragment: RestaurantsFragment)
    fun inject(menuFragment: MenuFragment)
}
